//
// Handles highlighting of finding locations in the code view.
//

"use strict";

var findingIdx; // Stores the index of the currently viewed finding.
var currentLoc; // Stores the currently highlighted location.

// Attach highlight handler.
$(window).ready(updateHighlight);
$(window).on("hashchange", updateHighlight);

/*
 * Highlights finding locations if highlight anchor or no anchor passed.
 */
function updateHighlight() {
    var anchor = window.location.hash;
    if (!anchor) {
        highlightAll(findingsArray);
    } else if (anchor.substring(0, 2) == '#/') {
        highlightSingle(findingsArray, anchor);
    }
}

/*
 * Highlights all locations in the currently displayed Java source file.
 */
function highlightAll(findings) {
    var srcFilePath = $('pre').attr('data-sourcefile');
    var locs = locationsByFilename(findings)[srcFilePath];
    findingIdx = -1;        // Multiple findings selected.
    currentLoc = undefined; // Multiple (primary) locations could be highlighted.
    highlight(locs);
    addTooltips(locs);
}

/*
 * Highlights the location of the finding, specified in the anchor. Does nothing
 * if finding not found. Uses primary location if location not found.
 */
function highlightSingle(findings, anchor) {
    var params = extractParams(anchor);
    var fndIdx = params.fnd;
    var finding = findings[fndIdx];
    var locIdx = params.loc ? params.loc : 0;

    if (!finding) {
        return;
    }
    var loc = finding.locations[locIdx];

    // Clear when new finding selected and page not loading for the first time.
    if (findingIdx !== undefined && findingIdx !== fndIdx) {
        clearHighlight();
    }

    // Clears currently highlighted location if it is not for display in the
    // Detail View (e.g. a guard location).
    if (currentLoc && !currentLoc.msg) {
        $('div[class~="' + currentLoc.line + '"]', document).removeClass('deep');
    }

    findingIdx = fndIdx;    // Single finding selected.
    currentLoc = loc;       // Update currently highlighted location.
    setFocus(loc.line);

    var locs = filter(finding.locations, $('pre').attr('data-sourcefile'));
    highlight(locs, loc);
    addTooltips(locs);
}

/*
 * Highlights locations from the locations array with a lighter colour. Primary
 * locations are highlighted with a deeper colour. If passed, the inFocus
 * location is highlighted with a deeper colour and everything else with a
 * lighter colour.
 */
function highlight(locations, inFocus) {
    var loc, isPrimary;
    for (var i in locations) {
        loc = locations[i];
        isPrimary = loc.key === '0';
        highlightLine(loc.line, !inFocus && isPrimary);
    }
    if (inFocus) {
        highlightLine(inFocus.line, true);
    }
}

/*
 * Highlights specified line with a lighter color. A deeper colour is used if
 * the optional isInFocus boolean parameter equals true.
 */
function highlightLine(line, isInFocus) {
    var elem = $('div[class~="' + line + '"]', document);
    elem.removeClass(isInFocus ? 'light' : 'deep');
    elem.addClass(isInFocus ? 'deep' : 'light');
}

function clearHighlight() {
    $('div').filter('.deep').each(function () {
        $(this).removeClass('deep');
    });
    $('div').filter('.light').each(function () {
        $(this).removeClass('light');
    });
}

/*
 * For each location with a description, adds a description tooltip.
 */
function addTooltips(locations) {
    var loc;
    for (var i in locations) {
        loc = locations[i];
        if (loc.msg) {
            $('div[class~="' + loc.line + '"]', document).attr('title', loc.msg);
        }
    }
}

function setFocus(line) {
    var lineOffset = $('div[class~="' + line + '"]').offset().top;
    var scrollOffset = Math.max(0, lineOffset - $(window).height() / 2);
    $(document).scrollTop(scrollOffset);
}

/*
 * Parses the anchor string to return finding and location indices.
 */
function extractParams(anchor) {
    var params = {};
    var array = anchor.substring(2).split('/');
    params.fnd = array[0]; // Finding index.
    params.loc = array[1]; // Optional location index.
    return params;
}

/*
 * Filter out locations not originating from the Java source file identified by
 * srcFilePath - the relative path from its source directory.
 */
function filter(locations, srcFilePath) {
    var filtered = [];
    var loc;
    for (var j in locations) {
        loc = locations[j];
        if (loc.line && loc.msg && sourceFile(loc) === srcFilePath) {
            filtered.push(loc);
        }
    }
    return filtered;
}

/*
 * Returns the path to the Java source file from which the specified location
 * originates. Path is relative to the source directory.
 */
function sourceFile(loc) {
    var pkgPath = loc.classname.slice(0, loc.classname.lastIndexOf('.') + 1);
    return pkgPath.replace(/\./g, '/') + loc.filen;
}

/*
 * Returns a map of locations grouped by relative source file path.
 */
function locationsByFilename(findings) {
    var locations = {};
    var finding, loc, srcFilePath;
    for (var i in findings) {
        var finding = findings[i];
        for (var j in finding.locations) {
            loc = finding.locations[j];
            if (!loc.msg && j !== 0) {
                continue;
            }
            srcFilePath = sourceFile(loc);
            if (locations[srcFilePath] === undefined) {
                locations[srcFilePath] = [];
            }
            locations[srcFilePath].push(loc);
        }
    }
    return locations;
}

