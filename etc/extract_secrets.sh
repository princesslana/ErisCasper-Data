tar#!/usr/bin/env bash

set -e

openssl aes-256-cbc -K $encrypted_e6416518862a_key -iv $encrypted_e6416518862a_iv -in etc/codesigning.asc.enc -out etc/codesigning.asc -d
gpg --fast-import etc/codesigning.asc
