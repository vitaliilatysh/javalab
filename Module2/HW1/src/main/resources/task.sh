#!/bin/bash

#1.1 Change your system, global and repository config (add some values). Inspect how it is override.
echo "----->Git global config. Before<------"
echo "$(git config --global --list)"
echo "----->Git global config. Adding value<------"
echo "$(git config --global --type path --add current.dir $(pwd))"
echo "----->Git global config. After<------"
echo "$(git config --global --list)"

echo "----->Git local config. Before<------"
echo "$(git config --local --list)"
echo "----->Git global config. Adding value<------"
echo "$(git config --local --type bool --add file.exist true)"
echo "----->Git local config. After<------"
echo "$(git config --local --list)"

echo "----->Git system config. Before<------"
echo "$(git config --system --list)"
echo "----->Git global config. Adding value<------"
var="task5"
echo "$(git config --system --type int --add string.length ${#var})"
echo "----->Git system config. After<------"
echo "$(git config --system --list)"

#1.2 Add some aliases in your own opinion.
git config --global alias.st status
git config --global alias.ci commit
echo "$(git config --global --list)"
