#!/usr/bin/env node

const fs = require('fs');
const { execSync } = require('child_process');

const CHANGELOG_FILE = 'CHANGELOG.md';

/**
 * Get files changed in a commit
 */
function getFilesInCommit(hash) {
  try {
    const output = execSync(`git show --name-only --pretty=format: ${hash}`, {
      encoding: 'utf8'
    });
    return output
      .split('\n')
      .filter(line => line.trim() && !line.startsWith('src/main/resources/test'))
      .slice(0, 5); // Limit to first 5 files
  } catch {
    return [];
  }
}

/**
 * Extract commit hash from changelog link
 */
function extractHashFromLink(link) {
  const match = link.match(/\(([a-f0-9]+)\(\)\)/);
  return match ? match[1] : null;
}

/**
 * Enhance changelog with file information
 */
function enhanceChangelog() {
  try {
    let content = fs.readFileSync(CHANGELOG_FILE, 'utf8');

    // Find all commit references and add file information
    const commitRegex = /\* (.*?)\s+\(\[([a-f0-9]+)\]\(.*?\)\)/g;

    content = content.replace(commitRegex, (match, message, hash) => {
      const files = getFilesInCommit(hash);
      let result = match;

      if (files.length > 0) {
        // Filter out test files and add to changelog
        const displayFiles = files
          .filter(f => !f.includes('test') && f.trim())
          .slice(0, 3);

        if (displayFiles.length > 0) {
          result += ` - Files: ${displayFiles.map(f => '`' + f + '`').join(', ')}`;
        }
      }

      return result;
    });

    fs.writeFileSync(CHANGELOG_FILE, content, 'utf8');
    console.log('✓ Enhanced changelog with file information');
  } catch (error) {
    console.error('Error enhancing changelog:', error.message);
    // Don't fail the release, just warn
    process.exit(0);
  }
}

enhanceChangelog();
