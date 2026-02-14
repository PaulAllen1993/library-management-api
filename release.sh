#!/bin/bash

# Build the release-it Docker image
docker build -f Dockerfile.release-it -t release-it-tool .

# Run release-it in container with project mounted
# Mount the current directory and set git config
docker run --rm -it \
  -v $(pwd):/app \
  -w /app \
  -e GITHUB_TOKEN=$GITHUB_TOKEN \
  release-it-tool "$@"