class_path="$(pwd)"
rm_local_class() {
    echo locally untracking $@
    $config update-index --skip-worktree "$class_path/$@"
    rm -r "$class_path/$@"    
}

rm_local_class Ethics
rm_local_class Intro_to_AI
rm_local_class Operating_Systems
rm_local_class Organization_of_programming_languages

