#!/usr/bin/env sh

set -eux

mill root.packageIt
tar -cvzf dist.tar.gz -C out/root/packageIt/dest/ .
