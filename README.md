# COMP24011 2026 Lab1

This is the repo for COMP24011 labs in the academic session 2026-27.

This branch holds the materials for the `lab1` assignment.
You can always return to this branch with the command
```
git checkout lab1
```

There is a Unix shell refresh script to fetch the lab materials when they are released.
You **need to** run the refresh script **before** you start working on the assignment.
This should be done with the command
```
./refresh.sh
```

There is a `manual.pdf` document with instructions for the `lab1` assignment.
This manual specifies which files make up the solution to this exercise.
It also details how you can use the command line tools included in the lab materials to debug your code.

To successfully submit your assignment you **have to** commit the solution files, tag these for submission as `lab1_sol`, and then push both the commit and the tag to your repo.
On a typical lab machine, this usually involves the following sequence of commands
```
git add -A .
git commit
git tag lab1_sol
git push origin
git push --tags origin
```

Note that `git` tags are case sensitive, so make sure that you use the **correct** `git tag` command as shown above.
You **must not** modify the `lab1_sol` submission tag after your deadline has closed, trying to do this goes against the academic code of conduct and will be detected from the records of the CS GitLab server.

If you have placed additional files in this directory, you **should not** add those for submission with your solution.
In this case you will **need to** adapt the above `git add` command to only include the solution files, because submitting large files can make the solution inaccessible for marking.

Please ask for support in the lab sessions if you're unsure about any lab instructions or submission process.
If you are unfamiliar with coursework submission on CS GitLab, there are [submission guidelines for the course](https://canvas.manchester.ac.uk/courses/86646/pages/submission-guidelines) on Canvas.

[modeline]: # ( vim:set spell spl=en: )
