# Library Management API

## Development

### Pre-commit Hooks

This project uses [pre-commit](https://pre-commit.com/) to ensure code quality before commits.

#### Setup

Pre-commit is already installed. To run hooks manually:

```bash
pre-commit run --all-files  # Run on all files
pre-commit run              # Run on staged files only
```

The hooks will automatically run on every `git commit` and check:

- Trailing whitespace and end-of-file fixes
- YAML syntax validation
- Large file detection
- Merge conflict markers
- Maven compilation
- Maven tests
- Code formatting (Spotless - if configured)

#### Adding Pre-commit to PATH

Pre-commit has been added to your PATH automatically. You can now use:

```bash
pre-commit run --all-files  # Run on all files
pre-commit run              # Run on staged files only
```

If you open a new terminal and `pre-commit` is not found, run:

```bash
source ~/.zshrc
```

## Releases

This project uses [release-it](https://github.com/release-it/release-it) for automated versioning and releases.

### Automatic Releases (GitHub Actions)

Releases are automatically created when you push to the `main` branch. The workflow uses the **Angular commit convention** to determine version bumps:

#### Commit Message Format:
```
type(scope): description

[optional body]

[optional footer]
```

#### Version Increment Rules:
- **`fix:` or `perf:`** → Patch version (e.g., `0.0.1` → `0.0.2`)
- **`feat:`** → Minor version (e.g., `0.0.1` → `0.1.0`)
- **`BREAKING CHANGE:`** → Major version (e.g., `0.0.1` → `1.0.0`)

#### Examples:
```
fix: resolve null pointer in checkout
feat: add user authentication system
feat: implement book search API
BREAKING CHANGE: remove deprecated endpoints
```

The workflow:
- Builds the release-it container
- Analyzes commit messages since last release
- Determines appropriate version bump
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
