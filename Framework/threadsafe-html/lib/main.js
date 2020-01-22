/*jslint browser: true*/
/*global $, console, jQuery*/

"use strict";

var browserVersion = navigator.appVersion.indexOf("MSIE") != -1
    ? parseFloat(navigator.appVersion.split("MSIE")[1]) : 999;

/*
 * Application starting point.
 */
$(window).ready(function() {
    var target = parseDirectLink();
    var grouping = target ? target.grouping : 'by-type';
    initialiseIndexPage(index_findings(findingsArray), grouping, target);
});

$(window).on('hashchange', function() {
    if (window.location.hash === '') {
        return;
    }
    loadDirectLinkTarget(parseDirectLink());
});

/*
 * Corrects Package View size on window resize. This is needed as the Package
 * View size is in % of its parent div and dummydiv has a static size.
 */
$(window).resize(function() {
    if ($('.package_explorer').is(":visible")) {
        resizePackageFrames();
    }
});

function loadDirectLinkTarget(directLinkTarget) {
    if (!directLinkTarget) {
        return;
    }
    var findings = index_findings(findingsArray);
    var finding = toFinding(findings, directLinkTarget);
    if (directLinkTarget.grouping == $('.selector').val()) {
        $('.package_explorer').hide();
        if (finding) {
            updateFinding(finding, directLinkTarget.locationKey);
        }
        selectInTree(directLinkTarget);
        $('.findings').show();
        return;
    }
    initialiseIndexPage(findings, directLinkTarget.grouping, directLinkTarget);
}

function isGroupingValid(grouping) {
    var valid = ['by-type', 'by-class', 'by-cat', 'by-sev'];
    return $.inArray(grouping, valid) !== -1;
}

function initialiseIndexPage(findings, grouping, directLinkTarget) {
    $('.package_explorer').hide();
    $('.findings').hide();      // Minimises reflows.
    var tree = $.jstree._reference('.findings');
    if (tree) {
        tree.destroy();
    }
    $('.findings').empty();

    var finding = toFinding(findings, directLinkTarget);
    if (finding) {
        updateFinding(finding, directLinkTarget.locationKey);
    } else {
        loadSummary(findings);  // Load Summary page by default.
    }

    // Create list. Convert it to a tree with jstree on $(document).ready().
    $('.findings').append(createFindingsList(findings, grouping));
    $(createTree);

    // Finalise the index page after the tree is ready.
    $('.findings').on('loaded.jstree', function () {
        finishIndexPage(findings, grouping, directLinkTarget);
    });
}

/*
 * Creates and returns the findings list.
 */
function createFindingsList(findings, grouping) {
    var prop, group, image, name, node, leaves;
    var grouped = findings[grouping];

    var sortedGroupNames = [];
    var map = {};           // { groupName: [groupProperty1, ...], ... }
    for (prop in grouped) {
        name = groupName(grouped[prop], grouping, prop);
        if (map[name] === undefined) {
            map[name] = []; // Stores props mapping to the same UI group name.
            sortedGroupNames.push(name);
        }
        map[name].push(prop);
    }
    sortedGroupNames.sort();

    var tree = $('<ul class=main></ul>');
    for (var i in sortedGroupNames) {
        name = sortedGroupNames[i];
        for (var j in map[name]) {
            prop = map[name][j];
            group = grouped[prop];

            // Create group node.
            image = grouping === 'by-class' ?
                    '<img style="width:1em; height:1em;" src="ico/java.png" /> ' :
                    get_severity_icon(group[0]) + ' ';
            node = $('<li><a>' + image + name + '</a></li>');

            // Create leaves, attach on click handler.
            leaves = createGroup(group);
            $('a', leaves).click(function (e) {
                var url = $(e.target).attr('href');
                var target = parseAnchor(url);
                var finding = findings['by-id'][target.fndIdx];
                updateFinding(finding);
            });

            // Append leaves to group, group to tree.
            node.append(leaves);
            tree.append(node);
        }
    }
    return tree;
}

function groupName(findingsGroup, grouping, prop) {
    var representative = findingsGroup[0];
    if (grouping === 'by-type') {
        name = rules[representative.errortype].name;
    } else if (grouping === 'by-class') {
        name =  representative.primary().filen;
    } else {
        name = capitalize(prop);
    }
    return name + ' (' + findingsGroup.length + ')';
}

function createGroup(findings) {
    var finding, url;
    var group = $('<ul></ul>');
    for (var i = 0; i < findings.length; i++) {
        finding = findings[i];
        url = locationUrl(finding);
        group.append('<li><a href="' + url + '" target="classFrame">'
                + get_severity_icon(finding) + ' ' + finding.msg + '</a></li>');
    }
    return group;
}

/*
 * Creates a jstree from the findings list.
 */
function createTree() {
    // Set path to the themes.
    $.jstree._themes = "themes/";

    // Create tree, a heavily modified default theme used.
    $(".findings").jstree({
        "plugins" : ["themes", "html_data", "ui", "crrm"],
        "themes": {
            "theme": "default",
            "dots": false,
            "icons": false
        },
        "core" : { "animation" : 200 }
    });
}

/*
 * Completes the index page initialisation. Creates the Toolbar, sets up the
 * findings tree, shows the Findings View.
 */
function finishIndexPage(findings, grouping, directLinkTarget, e, data) {
    initialiseTree(directLinkTarget);
    setTimeout(fixWidthOuter, 0);   // Execute after this function.

    // Add group by drop-down.
    $('.findings').prepend(
            "<div class='groupby'><b>Group by:</b><select class='selector'> \
            <option class='by-type' value='by-type'>Type</option> \
            <option class='by-cat' value='by-cat'>Category</option> \
            <option class='by-sev' value='by-sev'>Severity</option> \
            <option class='by-class' value='by-class'>Class</option> \
            </select></div>");
    $('.' + grouping).attr('selected', 'selected');

    // Add navigation links.
    var navigationtext = "<div class=dummydiv> \
            <span class=home_button><a>Summary</a></span> \
            <span class=package_view><a>Packages</a></span> \
            <span class=finding_view><a>Findings</a></span> \
            </div>";
    $('.findings').prepend(navigationtext);
    $('.home_button', '.findings').addClass('inactive');
    $('.finding_view', '.findings').removeClass('inactive');
    $('.package_view', '.findings').addClass('inactive');

    // Attach handlers.
    $('.selector').change(function() {
        initialiseIndexPage(findings, $(this).val());
    });
    $('.home_button', '.findings').click(function() {
        loadSummary(findings);
        $.jstree._reference('.findings').close_all();
    });
    $('.package_view', '.findings').click(function() {
        // Add navigation links to the Package View.
        if ($('.package_explorer').find('.dummydiv').length === 0) {
            $('.package_explorer').prepend(navigationtext);
            $('.home_button', '.package_explorer').addClass('inactive');
            $('.finding_view', '.package_explorer').addClass('inactive');
            $('.package_view', '.package_explorer').removeClass('inactive');

            $('.finding_view', '.package_explorer').click(function() {
                $('.package_explorer').hide();
                $('.findings').show();
            });
            $('.home_button', '.package_explorer').click(function() {
                loadSummary(findings);
                $.jstree._reference('.findings').close_all();
                $('.package_explorer').hide();
                $('.findings').show();
            });
        }
        $('.findings').hide();
        resizePackageFrames();
        $('.package_explorer').show();
        // Allow use of Direct Link from Package View. Triggers 'onhashchange'.
        window.location.hash = '';
    });
    $('.findings').show();  // Findings View is ready.
}

function initialiseTree(directLinkTarget) {
    selectInTree(directLinkTarget);
    stripeTree();

    // Attach 'on node open' and 'on node close' handlers.
    $('.findings').bind("open_node.jstree close_node.jstree", function(e) {
        stripeTree();
    }).bind("open_node.jstree", function(event, data) {
        setTimeout(function() {
            fixWidthInner(data.rslt.obj.attr("class"));
        }, 0);  // Execute after this function.

    // Attach 'on click' handlers for expanding and closing group nodes.
    }).delegate(".jstree-open>a", "click.jstree", function(event) {
        $.jstree._reference(this).close_node(this, false, false);
    }).delegate(".jstree-closed>a", "click.jstree", function(event) {
        $.jstree._reference(this).open_node(this, false, false);
    });
}

/*
 * Loads code for a finding into classFrame given the Direct Link target object.
 */
function toFinding(findings, directLinkTarget) {
    if (!directLinkTarget) {
        return undefined;
    }
    return findings['by-id'][directLinkTarget.findingIdx];
}

/*
 * Expands the group node and selects the fleaf node that correspond to the
 * targeted finding. Clears any previous tree slecection.
 */
function selectInTree(directLinkTarget) {
    var nodes = treeNodes(directLinkTarget);
    if (nodes) {
        clearTreeSelection();   // Clear highlighting on selected nodes.
        selectNodes(nodes);     // Expand group node, highlight leaf node.
    }
}

/*
 * Given a Direct Link target object, returns an object with a group and a leaf
 * node that correspond to the targeted finding in the tree.
 */
function treeNodes(target) {
    if (!target) {
        return undefined;
    }
    var group = $('.findings > ul > li:eq(' + target.groupIdx + ')');
    var leaf = group.find('ul li:eq(' + target.leafIdx + ') a');
    if (!group.length || !leaf.length) {
        return undefined;
    }
    return {
        group: group,
        leaf: leaf
    };
}

/*
 * Expands the group node and highlights the leaf node of an object such as
 * the one returned by treeNodes().
 */
function selectNodes(nodes) {
    $.jstree._reference(nodes.group).open_node(nodes.group, false, false);
    $(nodes.leaf).addClass('jstree-clicked');
}

/*
 * Clears highlighting on all clicked nodes, if any.
 */
function clearTreeSelection() {
    $('.jstree-clicked').removeClass('jstree-clicked');
}

/*
 * Loads the summary page if not already loaded.
 */
function loadSummary(findings) {
    // Load Summary page.
    $('.classFrame').attr("src", 'summary.html');

    // Calculate finding statistics.
    var bySeverity = findings['by-sev'];
    var blkr = count_map_el(bySeverity['blocker']);
    var crit = count_map_el(bySeverity['critical']);
    var maj = count_map_el(bySeverity['major']);
    var min = count_map_el(bySeverity['minor']);
    var info = count_map_el(bySeverity['info']);
    var total = count_map_el(findings['by-id']);

    // Show statistics in the Detail View.
    $('.details').empty();
    $('.details').append("<p class='Summary'>Potential problems found:</p> \
            <p class='Summary'><img src='ico/severity/blocker.png' /> \
            <b>Blocker</b> " + blkr + "</p> \
            <p class='Summary'><img src='ico/severity/critical.png' /> \
            <b>Critical</b> " + crit + "</p> \
            <p class='Summary'><img src='ico/severity/major.png' /> \
            <b>Major</b> " + maj + "</p> \
            <p class='Summary'><img src='ico/severity/minor.png' /> \
            <b>Minor</b> " + min + "</p> \
            <p class='Summary'><img src='ico/severity/info.png' /> \
            <b>Info</b> " + info + "</p> \
            <p><b>Total</b> " + total + '</p>');
}

/*
 * Updates the Detail and Source Code views with details relevant to the
 * specified finding and location. Hides rule documentation if open.
 */
function updateFinding(finding, locationKey) {
    var url = locationUrl(finding, locationKey);
    $('.classFrame').attr('src', url);      // Reload frame.

    $('.details').empty();
    displaylocations(finding, locationKey); // Add content to Detail View.

    $('.more_info').empty();
    $('.more_info').hide();
    $('.classFrame').css('height', '100%');
    $('.classFrame').css('border', 'none');
}

/*
 * Function that populates the bottom left div (.details)
 */
function displaylocations(finding, locationKey) {
    var prev_classname = "";
    var rule = rules[finding.errortype];
    var primary = finding.primary();

    // The generic description appears on top of the .details, same as the text
    // on the root node, containing the leaf.
    $('.details').append(
            "<p class='generic_detail'><b>" + rule.name + "</b></p> \
            <p class='specific_detail'>" + finding.msg + '</p>');

    // Append location links, grouped and sorted by filename.
    $('.details').append(createLocationLinks(finding));


    $('.details').append("<p class=launchers></p>");

    // Display Accesses link if there are guards.
    if (finding.guards !== undefined && finding.guards.length !== 0) {
        $('.details > .launchers').append("<span class='guardview hidden'> \
                <img src='ico/guard.png' />Accesses</span>");

        $('.details > .launchers > .guardview').click(function() {
            var primary_url;

            if ($(this).hasClass('hidden') === true) {
                $('.more_info').show();
                $('.more_info').css('height', '30%'); // Restore height.
                $(this).removeClass('hidden');
                // Reset learn more.
                $('.details > .launchers > .learn_more').attr('title', 'Learn more');
                $('.classFrame').css('height', '70%');
                // Restore the border now that it is no longer minimised.
                $('.classFrame').css('border-bottom', '1px solid');
                $('.classFrame').css('border-bottom-color', '#CACDC5');
                $('.more_info').empty();
                // Guard_description.
                primary_url = locationUrl(finding);
                $('.more_info').append(
                        '<p class="guard_descr">Guards for access to ' + primary.tag
                        + ' <a class="prim_loc" href=' + primary_url + ' target="classFrame">'
                        + primary.classname.split('.').slice(-1)[0] + '.' + primary.name_attr
                        + "</a>:<span class='close_button'> \
                            <img src='ico/close.png' title='Close'/> \
                          </span></h3><br/></p>");
                // Guards table.
                $('.more_info').append(draw_guards(finding));
                // Make highlighting work in the guards view as well.
                $('.more_info').find('a').each(function() {
                    $(this).click(function() {
                        // Click the link in .details
                        $('.details').find('a[href="' + $(this).attr('href') + '"]').click();
                    });
                });
                // Add a close button inside the div.
                $('.close_button', '.more_info').click(function() {
                    $('.details > .launchers > .learn_more').attr('title', 'Learn more');
                    $('.details > .launchers > .guardview').addClass('hidden');
                    $('.more_info').hide();
                    $('.classFrame').css('height', '100%');
                    $('.classFrame').css('border', 'none');
                });
            } else {
                $('.more_info').hide();
                $(this).addClass('hidden');
                // Reset learn more, just in case.
                $('.details > .launchers > .learn_more').attr('title', 'Learn more');
                $('.classFrame').css('height', '100%');
                $('.classFrame').css('border', 'none'); // Otherwise width not correct.
            }
        });
    }

    $('.details > .launchers').append("</br> \
            <span class='learn_more' title='Learn more'> \
            <img src='ico/question.png' />Rule description</span>");

    // Show/hide more_info
    $('.details > .launchers > .learn_more').click(function() {
        if ($(this).attr('title') === "Learn more") {
            $('.more_info').empty();

            $('.more_info').append('<h3>' + rule.name
                + '</h3><span class="close_button"><img src="ico/close.png" title="Close"/></span>'
                + rule.description);

            $('.more_info').show();
            $('.more_info').css('height', '30%'); // Restore height
            $(this).attr('title', 'Hide');
            // Reset guards.
            $('.details > .launchers > .guardview').addClass('hidden');
            $('.classFrame').css('height', '70%');
            // Restore the border now that it is no longer minimised.
            $('.classFrame').css('border-bottom', '1px solid');
            $('.classFrame').css('border-bottom-color', '#CACDC5');
            // Add a close button inside the div.
            $('.close_button', '.more_info').click(function() {
                $('.details > .launchers > .learn_more').attr('title', 'Learn more');
                $('.details > .launchers > .guardview').addClass('hidden');
                $('.more_info').hide();
                $('.classFrame').css('height', '100%');
                $('.classFrame').css('border', 'none');
            });
        } else {
            $('.more_info').hide();
            $(this).attr('title', 'Learn more');
            $('.details > .launchers > .guardview').addClass('hidden');
            $('.classFrame').css('height', '100%');
            $('.classFrame').css('border', 'none'); // Otherwise width not drawn correctly.
        }
    });

    /*
     * The generic detail that appears on the bottom of the .details div. Append
     * AFTER the table is drawn and all JS has finished executing.
     */
    setTimeout(function() {
        var ico = get_severity_icon(finding);
        var url = directLink(finding, locationKey);
        var rule = rules[finding.errortype];
        var generic_detail = '<p class="generic_detail">'
                + '<b>Category:</b> ' + rule.category
                + '<br/><b>Severity:</b> ' + ico + capitalize(finding.severity)
                + '<br/><b>Type:</b> ' + finding.errortype
                + '<br/><b class="width">DirectLink:</b> <input type=text readonly="readonly" value="'
                + url + '" class="linkcopy"></p>';

        $('.details').append(generic_detail);
        // Make the width consistent across window sizes.
        $('.linkcopy').width($('.details').width() - $('.width').width() - 30);
        // Select all text on click.
        $('.linkcopy').click(function() {
            $(this).select();
        });
        pushHistoryState(url);
    }, 0);
}

/*
 * Creates a paragraph of tables with links to the locations of the specified
 * finding.
 */
function createLocationLinks(finding) {
    var i, loc;

    var filtered = [];
    for (i = 0; i < finding.locations.length; i++) {
        loc = finding.locations[i];
        if (loc.msg) {
            filtered.push(loc);
        }
    }

    var locations = $('<p class="locations"></p>');
    var grouped = groupByFilename(filtered);
    for (i = 0; i < grouped.length; i++) {
        // Append filename (from representative) and table with links.
        locations.append("<span class='new_filename'> \
                <img style='width:1em; height:1em;' src='ico/java.png' />&nbsp;"
                + grouped[i][0].filen + '</span>');
        locations.append(createLinkTable(finding, grouped[i]));
    }
    // Update Direct Link when selecting locations.
    $('a', locations).click(function(e) {
        var params = parseAnchor($(e.target).attr('href'));
        var url = directLink(finding, params.locKey);
        $('.linkcopy').val(url);
        pushHistoryState(url);
    });
    return locations;
}

/*
 * Creates a table with rows of links for the Detail View.
 */
function createLinkTable(finding, locations) {
    var loc, linkText, isNotPrimary, url, common;

    var table = $('<table cellspacing="0" />');
    for (var i = 0; i < locations.length; i++) {
        loc = locations[i];
        linkText = loc.line ? loc.line : '?';

        url = locationUrl(finding, loc.key);
        if (!url) {
            table.append('<tr><td class=num>' + linkText + '</td><td>' + loc.msg
                    + '</td></tr>');
            continue;
        }
        common = ' href="' + url + '" target="classFrame">';
        table.append('<tr><td class=num><a'
                + common + linkText + '</a></td><td><a id="black_link"' // number link
                + common + loc.msg + '</a></td></tr>');                 // text link
    }
    return table;
}

/*
 * Returns the path to the Java source file from which the specified location
 * originates. Path is relative to the source directory.
 */
function sourceFile(loc) {
    var pkgPath = loc.classname.slice(0, loc.classname.lastIndexOf('.') + 1);
    return pkgPath.replace(/\./g, '/') + loc.filen;
}

function pushHistoryState(url) {
    if (browserVersion >= 10) {
        window.history.pushState({}, '', url);
    }
}

/*
 * Returns a URL to the location specified in the optional locationKey
 * parameter. The primary location is chosen when locationKey not specified. The
 * URL is relative to the document root and links to the transformed source file
 * from which the location originates.
 */
function locationUrl(finding, locationKey) {
    var primary = finding.primary();
    var loc = locationKey ? finding.locations[locationKey] : primary;

    // Remove 'isNotPrimary &&' from below once all primary locations are
    // guaranteed to have line numbers.
    var isNotPrimary = loc !== primary;
    if (!loc.filen || isNotPrimary && !loc.line) {
        return undefined;
    }
    var path = sourceFile(loc);
    var url = 'jxr/' + path.split('.')[0] + '.html';    // Swap .java with .html
    url += '#/' + finding.index;                        // Append anchor.
    if (loc.line) {
        url += '/' + loc.key;
    }
    return url;
}

/*
 * Parses the anchor from the supplied URL and returns an object containing the
 * finding and location indices.
 */
function parseAnchor(fromUrl) {
    var anchor = fromUrl.slice(fromUrl.indexOf('#'));
    var arr = anchor.slice(2).split('/');
    return {
        fndIdx: arr[0], // Global finding index.
        locKey: arr[1]  // Optional location key.
    };
}

/*
 * Sorts locations. Primary location is always first. If lines are missing, the
 * location is put last. The rest are sorted depending on their line number.
 */
function locationsort(a, b) {
    if (a.key === '0' || b.line === undefined) {
        return -1;
    } else if (b.key === '0' || a.line === undefined) {
        return 1;
    } else {
        return Number(a.line) - Number(b.line);
    }
}

/*
 * Groups locations by Java source file path for display in the Detail View.
 * Groups are sorted alphabetically by source file path. Locations are sorted by
 * line number. Returns an array of location arrays.
 */
function groupByFilename(locations) {
    var i, loc, path, group;

    // Group by source file path.
    var groups = {};
    for (i = 0; i < locations.length; i++) {
        loc = locations[i];
        path = sourceFile(loc);
        if (groups[path] === undefined) {
            groups[path] = [];
        }
        groups[path].push(loc);
    }

    // Sort keys alphabetically in ascending order, primary path is first.
    var sorted = [];
    for (var path in groups) {
        sorted.push(path);
    }
    var primaryPath = sourceFile(locations[0]);
    sorted.splice($.inArray(primaryPath, sorted), 1);
    sorted.sort();
    sorted.unshift(primaryPath);

    // Sort locations by line number in ascending order.
    var result = [];
    for (i = 0; i < sorted.length; i++) {
        group = groups[sorted[i]];
        group.sort(locationsort);
        result.push(group);
    }
    return result;
}

/*
 * Generates an <img> tag with the appropriate severity icon.
 */
function get_severity_icon(finding) {
	return "<img src='ico/severity/" + finding.severity + ".png'/>";
}

/*
 * Capitalizes first letter of a string.
 */
function capitalize(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

/*
 * Makes the tree displayed to be stripy for visibility. The function works by
 * iterating through all visible lines and using a counter to know which ones
 * to highlight and which ones to leave the same colour.
 */
function stripeTree() {
    var counter = 0;
    var classes;

    // Go through all the root nodes.
    $('.findings').find('ul > li').each(function() {
        classes = get_classes($(this));
        if (contains('jstree-closed', classes)) {
            if (counter % 2 === 0) {
                $(this).css('background-color', 'WhiteSmoke');
            } else {
                // Clear BG colour.
                $(this).css('background-color', 'white');
            }
            counter++;
        // Now we are at an open node. We need to count its ul elements in order
        // to paint them and then continue.
        } else if (contains('jstree-open', classes)) {
            if (counter % 2 === 0) {
                $(this).css('background-color', 'WhiteSmoke');
            } else {
                // Clear BG colour.
                $(this).css('background-color', 'white');
            }
            counter++;
            // Check each leaf of the expanded node now.
            (function(domobj) {
                domobj.find('li').each(function() {
                    if (counter % 2 === 0) {
                        $(this).css('background-color', 'WhiteSmoke');
                    } else {
                        // Clear BG colour.
                        $(this).css('background-color', 'white');
                    }
                    counter++;
                });
            })($(this));
        }
    });
}

/*
 * In case we are dealing with dom elements that contain more than one classes,
 * use this to split them into an array so that you can check if you are looking
 * for individual one
 */
function get_classes(elem) {
    return $(elem).attr('class').split(/\s+/);
}

/*
 * Check if an element is in a list
 */
function contains(item, arr) {
    for (var i in arr) {
        if (item === arr[i]) {
            return true;
        }
    }
    return false;
}

/*
 * Counts the number of elements in an array-map type of object
 */
function count_map_el(array_map) {
    var counter = 0;
    for (var prop in array_map) {
        if (array_map.hasOwnProperty(prop)) {
            counter++;
        }
    }
    return counter;
}

/*
 * The function draws the guard view window onto the more_info div
 */
function draw_guards(finding) {
    var table = $('<table class="accesses">');
    var i, j, url;
    var guard, guard_name, loc;
    var row, acc_loc, acc_text, acc_td_arr;

    // Create row with guard names.
    var guards = $('<tr class="guards"><td></td></tr>');
    var sortedByKey = finding.guards;
    sortedByKey.sort(function(a, b) {
        return Number(a.key) - Number(b.key);
    });

    for (i = 0; i < sortedByKey.length; i++) {
        guard = sortedByKey[i];
        guard_name = escapeHtml(guard.name);
        loc = finding.locations[guard.locationRef];
        url = loc ? locationUrl(finding, loc.key) : undefined;
        if (!url) {
            guards.append('<td>' + guard_name + '</td>');
            continue;
        }
        guards.append('<td><a href="' + url + '" target="classFrame">'
            + guard_name + '</a></td>');
    }

    // Make the last cell on each row extend to fill in the div
    guards.children().last().css('width', '99%');
    table.append(guards);

    // Sort acccesses array:
    finding.accesses = accessessort(finding);

    // Now add the accesses
    var unknown = '?';
    for (i = 0; i < finding.accesses.length; i++) {
        acc_loc = finding.locations[Number(finding.accesses[i].location_attr)];

        // In case we don't have a message for this location, don't display it.
        if (acc_loc.msg === undefined) {
            continue;
        }

        row = $('<tr class="accesses' + i + '"></tr>');
        url = locationUrl(finding, acc_loc.key);
        acc_text = (acc_loc.filen ? acc_loc.filen : unknown) + ': '
                + (acc_loc.line ? acc_loc.line : unknown);
        if (url) {
            row.append('<td><a href=' + url + ' target="classFrame">'
                    + acc_text + '</a></td>');
        } else {
            row.append('<td>' + acc_text + '</td>');
        }

        // Add accesses text now
        acc_td_arr = [];
        for (j = 0; j < finding.accesses[i].guard_ref.length; j++) {
            acc_td_arr[Number(finding.accesses[i].guard_ref[j].key)] = finding.accesses[i].guard_ref[j].status_attr;
        }

        // Sometimes a guard is not mentioned so we have to add the text manually.
        for (j = 0; j < finding.guards.length; j++) {
            if (acc_td_arr[j] === undefined) {
                acc_td_arr[j] = 'Not Held';
            } else if (acc_td_arr[j] === 'sometimes') {
                acc_td_arr[j] = 'Maybe Held';
            } else if (acc_td_arr[j] === 'always') {
                acc_td_arr[j] = 'Always Held';
            }
        }

        // Output the TD rows;
        for (j = 0; j < acc_td_arr.length; j++) {
            row.append('<td class="' + acc_td_arr[j].split(' ')[0] + '">'
                    + acc_td_arr[j] + '</td>');
        }
        table.append(row);
    }
    // Return a striped table for visibility
    return (stripetable(table));
}

function stripetable(tabledom) {
    var counter = 0;
    tabledom.find('tr').each(function() {
        if ((counter % 2) === 0) {
            $(this).css('background-color', 'WhiteSmoke');
        }
        counter++;
    });
    return tabledom;
}

function accessessort(finding) {
    var new_accesses = [];

    // A new accesses array  more suitable for sorting.
    for (var i = 0; i < finding.accesses.length; i++) {
        new_accesses[i] = finding.accesses[i];
        new_accesses[i].old_index = i;
        new_accesses[i].line = finding.locations[Number(finding.accesses[i].location_attr)].line;
        new_accesses[i].filen = finding.locations[Number(finding.accesses[i].location_attr)].filen;
    }

    // Sort temp accesses.
    new_accesses.sort(function(a, b) {
        if (a.filen === b.filen) {
            return (Number(a.line) - Number(b.line));
        } else {
            return (Number(a.location_attr) - Number(b.location_attr));
        }
    });
    return new_accesses;
}

/*
 * Function to correctly apply size to the package explorer
 */
function resizePackageFrames() {
    selector = $('.package_explorer');
    var listframeheight, selector, dummyheight;

    dummyheight = selector.find('.dummydiv').height()*100/selector.height();
    listframeheight = (selector.height()/3 - selector.find('.dummydiv').height())*100/selector.height();
    $('.packageListFrame').height(String(listframeheight) + '%');
    //Leave all the rest for the packageFrame
    $('.packageFrame').height(String(100 - listframeheight - dummyheight -1) + '%');
}

/*
 * Finds nodes that contain words that are too big to be displayed properly. To
 * be used on inner tree nodes only.
 */
function fixWidthInner(nodeclass) {
    var aselector, targetwidth;

    $('.' + nodeclass.split(' ')[0]).find('li').each(function() {
        aselector = $(this).find('a').first();
        targetwidth = $(this).width();
        if (targetwidth < (aselector.width() + 38)) {
            fixWidth(aselector, targetwidth);
        }
    });
}

/*
 * Same as the above, except loops over all outer nodes and runs when the tree
 * is laoded.
 */
function fixWidthOuter() {
    var aselector, targetwidth;

    $('.jstree-no-dots').children().each(function() {
        aselector = $(this).find('a').first();
        targetwidth = $(this).width();
        if (targetwidth < (aselector.width() + 38)) {
            fixWidth(aselector, targetwidth);
        }
    });
}

/*
 * Makes the width of the aselector to be <= than the targetwidth
 */
function fixWidth(aselector, targetwidth) {
    var new_str_arr = [];
    var failed = true;
    var longest = "";
    var i, split_word_arr, longest_idx;

    /* The logic is the following:
     *  Find the longest word.
     *  Split it on InnerCapitaLetters (Inner Capital Letters) and
     *  Inner dots in the filename (if such a present).
     *  If none are present, split in the middle
     *  If the width is still not small enough, recurr
     */
    // Save ins and img
    var ins_tag = aselector.find('ins');
    var img_tag = aselector.find('img');
    var str_arr = aselector.text().split(' ');

    // Get the longest word
    for (i = 0; i < str_arr.length; i++) {
        if (longest.length < str_arr[i].length) {
            longest = str_arr[i];
            longest_idx = i;
        }
    }

    // Try to split it on InnerCapitalLetters
    split_word_arr = longest.replace(/(.(?=[A-Z]))/g, '$1,').split(',');

    // Check if we failed to reduce the word size
    if (split_word_arr[0] !== longest) {
        failed = false;
    }

    // If we failed to reduce word size, split on dots in the name
    if (failed) {
        split_word_arr = longest.replace(/\./g, ',.').split(',');
        // Check if we failed to reduce the word size
        if (split_word_arr[0] !== longest) {
            failed = false;
        }
    }

    // If we failed to reduce word size, split on underscore
    if (failed) {
        split_word_arr = longest.replace(/\_/g, '_,').split(',');
        // Check if we failed to reduce the word size
        if (split_word_arr[0] !== longest) {
            failed = false;
        }
    }

    // As a final effort split the longest word in half
    if (failed) {
        split_word_arr = [];
        split_word_arr[0] = longest.slice(0, Math.ceil(longest.length/2));
        split_word_arr[1] = longest.slice(Math.ceil(longest.length/2));
    }

    // Reconstruct the string
    for (i = 0; i < str_arr.length; i++) {
        if (i !== longest_idx) {
            new_str_arr.push(str_arr[i]);
        } else {
            new_str_arr = new_str_arr.concat(split_word_arr);
        }
    }

    // Change the text of the atag (and remove extra initial whitespace)
    aselector.text(new_str_arr.join(' ').slice(1));

    // Restore html
    aselector.prepend(img_tag);
    aselector.prepend(ins_tag);
}

/*
 * Constructs a Direct Link url for the currenly secelcted finding.
 */
function directLink(finding, locationKey) {
    var url = window.location.protocol + '//'
            + window.location.host + window.location.pathname;
    url = url.replace(/\/*$/g, ''); // Remove trailing slashes.
    return url + encodeTarget(finding, locationKey);
}

/*
 * Encodes the Direct Link target (currently selected finding) into a query
 * string.
 */
function encodeTarget(finding, locationKey) {
    // Get currently clicked node.
    var node = $('.jstree-clicked');
    var segments;
    // If no selection (when page accessed via a Direct Link).
    if (!node.attr('target')) {
        var target = parseDirectLink();
        segments = [target.grouping, target.groupIdx, target.leafIdx,
                target.findingIdx];
        if (target.locationKey !== undefined) {
            segments.push(target.locationKey);
        }
        return '#' + segments.join('/');
    }
    var innerList = node.closest('ul');
    var id = innerList.children().index(node.closest('li'));
    var group = innerList.closest('li');
    var outerList = group.closest('ul');
    var sortclass = outerList.children().index(group);
    segments = [$('.selector').val(), sortclass, id, finding.index];
    if (locationKey !== undefined) {
        segments.push(locationKey);
    }
    return '#' + segments.join('/');
}

/*
 * If a Direct Link used, parses it to create and return a target object.
 * Returns undefined when the parsing is unsuccessful.
 */
function parseDirectLink() {
    // Format is #grouping/rootNodeIdx/leafNodeIdx/findingIdx.
    var segments = document.location.hash.replace(/\#/g, '').split('/');
    var intRegex = /^\d+$/;
    if (!(segments.length === 4 || segments.length === 5)
            || !segments[0] || !isGroupingValid(segments[0])
            || !segments[1]
            || !segments[2] || !intRegex.test(segments[2])
            || !segments[3] || !intRegex.test(segments[3])
            || segments[4] && !intRegex.test(segments[4])) {
        return undefined;
    }
    var target = {};
    target.grouping = segments[0];
    target.groupIdx = segments[1];
    target.leafIdx = Number(segments[2]);
    target.findingIdx = Number(segments[3]);
    if (segments[4]) {
        target.locationKey = segments[4];
    }
    return target;
}

function escapeHtml(unsafe) {
    return unsafe
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;")
         .replace(/\//g, "&#x2F;");
}

