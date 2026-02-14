# ContalLibraryAPI

## Releases

This project uses [release-it](https://github.com/release-it/release-it) for automated versioning and releases.

### Automatic Releases (GitHub Actions)

Releases are automatically created when you push to the `main` branch. The workflow:
- Builds the release-it container
- Runs release-it with `--ci` flag
- Creates a GitHub release with changelog
- Pushes version tags

### Manual Releases

If you prefer manual control:

#### Prerequisites

- Docker
- GitHub Personal Access Token with `repo` permissions

#### Usage

1. Set your GitHub token:
   ```bash
   export GITHUB_TOKEN=your_github_token_here
   ```

2. Run the release script:
   ```bash
   ./release.sh
   ```

   Or for a specific version bump:
   ```bash
   ./release.sh --patch  # for patch version (0.0.1 -> 0.0.2)
   ./release.sh --minor  # for minor version (0.0.1 -> 0.1.0)
   ./release.sh --major  # for major version (0.0.1 -> 1.0.0)
   ```

The tool will:
- Update version in package.json
- Create a git tag
- Create a GitHub release with auto-generated changelog
- Push changes to the repository
