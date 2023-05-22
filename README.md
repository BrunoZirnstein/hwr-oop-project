# HWR OOP Lecture Project Template

[TODO]: # (Change README.md Headline to better fit to your project!)

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at HWR Berlin (summer term 2023).

> :warning: This code is for educational purposes only. Do not rely on it!

## Abstract

[TODO]: # (Write a short description of your project.)
[TODO]: # (State most important features.)
[TODO]: # (State the most interesting problems you encountered during the project.)

## Feature List

[TODO]: # (For each feature implemented, add a row to the table!)

| Number | Feature | Done | Responsible |
|--------|---------|-------|-------------|
| 1      | Tag Klasse       |   ✔️   | Niklas            |
| 2      | Hauptmenü Task anlegen      |       | Thomas            |
| 3      | Task Klasse       |   ✔️   | Bruno            |
| 4      | Projekt Klasse       |   ✔️   | Jason & Frauke            |
| 5      | CSV Read & Write       |   ✔️   | Niklas            |
| 6      | Task Klasse - optionale Parameter mit builder pattern      |   ✔️   | Bruno            |
| 7      | Projekt Klasse anpassen       |   ✔️   | Jason & Frauke            |
| 8      | ToDo Liste Klasse       |   ✔️   | Jason & Frauke            |
| 9      | Hauptmenü Tasks anlegen      |      | Thomas            |
| 10      | Todo Klasse mit Projekten      |   ✔️    | Bruno            |
| 11      | Update task       |     ✔️   | Jason & Frauke            |
| 12      | CSV Read & Write anpassen       |   ✔️      | Niklas            |
| 13      | refactor ToDo tests & code      |    ✔️    | Bruno            |
| 14      | refactor task tests & code       |       | Jason & Frauke            |
| 15      | Menü code an neue Classen & Struktur anpassen     |    ✔️    | Thomas            |
| 16      | refactor CSV Read & Write tests       |    ✔️     | Niklas            |
| 17      | Input Handler & Print Stream implementieren     |    ✔️    | Thomas            |
| 18      | Menü Tests erstellen     |       | Thomas            |


## Additional Dependencies

[TODO]: # (For each additional dependency your project requires- Add an additional row to the table!)

| Number | Dependency Name | Dependency Description | Why is it necessary? |
|--------|-----------------|------------------------|----------------------|
| 1      | /               | /                      | /                    |

## Instructions

[TODO]: # (Remove these instructions once you finished your fork's setup.)

Use a fork of this repository to do implement your project.

Remember to add this repository as a second remote repository (upstream) and pull from the correct remotes.
This is necessary, because we might apply changes to this template during the next month.

The following section describes how to add multiple remote repositories to your local repository, which is cloned from the fork.

### Multiple remote repositories

Your local repository should have a reference to both the fork (your own remote repository) and the original remote repository.
To configure your git remote repositories, use the `git remote` command set.

1. Clone your fork and go enter the repository.
```
git clone <fork-url>
cd <created-folder>
```
2. Now your fork is configured as primary remote repository (origin).
Next to origin, you should add the original repository as a second remote repository (upstream).
```
git remote add upstream <repository-url>
```
3. Verify that both remotes are configured correctly.
The following command should list both remotes: origin and upstream.
```
git remote -v
```
4. To fetch changes from all remote repositories, use:
```
git fetch --all
```
5. If there are interesting changes (in e.g. the `main` branch) to merge into your branch, use:
```
git pull upstream main
```
