#!/usr/bin/env sh

set -eux

mill show root[$1].packageIt | jq .path | xargs -I{} tar -cvzf dist.tar.gz -C {} .
