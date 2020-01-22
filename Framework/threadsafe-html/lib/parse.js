/*jslint browser: true*/
/*global $, console*/

"use strict";

/*
 * Adds properties to all findings and organises them in groups.
 */
function index_findings(findingsArray) {
    var findings = [];
    var n, finding, filename;

    findings['by-id'] = [];
    findings['by-type'] = [];
    findings['by-sev'] = [];
    findings['by-cat'] = [];
    findings['by-class'] = [];

    for (var i = 0; i < findingsArray.length; i++) {
        finding = findingsArray[i];
        finding.index = i;
        finding.primary = function () {
            return this.locations[0];
        };

        if (findings['by-type'][finding.errortype] === undefined) {
            findings['by-type'][finding.errortype] = [];
        }
        if (findings['by-sev'][finding.severity] === undefined) {
            findings['by-sev'][finding.severity] = [];
        }
        if (findings['by-cat'][finding.category] === undefined) {
            findings['by-cat'][finding.category] = [];
        }
        filename = sourceFile(finding.primary());
        if (findings['by-class'][filename] === undefined) {
            findings['by-class'][filename] = [];
        }

        findings['by-id'].push(finding);
        findings['by-type'][finding.errortype].push(finding);
        findings['by-sev'][finding.severity].push(finding);
        findings['by-cat'][finding.category].push(finding);
        findings['by-class'][filename].push(finding);
    }
    return findings;
}

/*
 * Returns the path to the Java source file from which the specified location
 * originates. Path is relative to the source directory.
 */
function sourceFile(loc) {
    var pkgPath = loc.classname.slice(0, loc.classname.lastIndexOf('.') + 1);
    return pkgPath.replace(/\./g, '/') + loc.filen;
}

