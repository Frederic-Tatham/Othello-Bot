# COMP24011 2026 Labs

This is the repo for COMP24011 labs in the academic session 2026-27.

There is a separate branch for each of the four lab assignments of the course.
These are called: `lab1`, `lab2`, `lab3`, `lab4`.
To access the materials for each lab you need to switch to the corresponding branch.
For example, you can switch to the branch of the first lab with the command
```
git checkout lab1
```

Every branch has a Unix shell refresh script to fetch lab materials when they are released.
You **need to** run the refresh script **before** you start working on the assignment.
This should be done with the command
```
./refresh.sh
```

Every branch has a `manual.pdf` document with instructions for the lab assignment.
This manual specifies which files make up the solution to the exercise.
It also details how you can use the command line tools included in the lab materials to debug your code.

You **have to** submit your solutions to this COMP24011 repo with the **correct** `git` tag for the lab assignment.
The `README.md` file on each branch describes the necessary `git` operations to successfully complete the submission on a typical lab machine.

Please ask for support in the lab sessions if you're unsure about any lab instructions or submission process.
If you are unfamiliar with coursework submission on CS GitLab, there are [submission guidelines for the course](https://canvas.manchester.ac.uk/courses/86646/pages/submission-guidelines) on Canvas.

[modeline]: # ( vim:set spell spl=en: )
