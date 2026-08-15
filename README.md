# azure_DataEngineerProj1

## Learning Notes: What Each Scala File Type Represents (Plain-English Explanation)

When you go to **File → New → Scala Class/File** in IntelliJ, you get six choices: Class, Trait, Case Class, Enum, Object, File. Here's what each one really *means*, using everyday comparisons — no prior programming background needed.

### Class — a blueprint for a house

A **class** is a **blueprint**, not an actual building. The blueprint alone doesn't shelter anyone — it's just the plan. Every time you write `new Car(...)` (or `Car(...)` via the shortcut), you're using that one blueprint to construct one real, physical house you can now live in, paint, or drive to work in. Build two houses off the same blueprint and they're still two separate houses — repainting one doesn't repaint the other. That's what "mutable state" means: our `Car` keeps a private `odometer` that belongs to that one car alone; a second `Car` you build has its own separate odometer starting at zero.

**In this project:** `typesdemo/Car.scala`. Every `Car("Tesla Model 3", 250)` you create is its own house built from the same blueprint, with its own mileage.

### Trait — a skill or certification a person can pick up

A **trait** is like a **certification** — "First-Aid Certified," "Licensed to Drive a Forklift." It isn't a whole blueprint for a person; it's one skill that can be bolted onto many different, unrelated kinds of things. A `Car` can hold both the "can move" certification and the "can honk" certification at once (`extends Movable with Honkable`), and a `Bike` can hold the "can move" certification too — the two vehicles don't need to be related in any other way. Some parts of the certification arrive already "taught" (a concrete method, ready to use as-is); other parts are left blank on purpose and each vehicle must fill them in for itself (an abstract method).

**In this project:** `typesdemo/Movable.scala`. Both `Car` and `Bike` carry the "can move" certification, but each says for itself *how* it moves — a car drives, a bike pedals.

### Case Class — a printed ID card / form

A **case class** is like a **printed ID card**, e.g. a driver's licence. Once it's issued, the card itself doesn't get edited — if your address changes, the office doesn't erase and overwrite your old card, it prints you a **new** card with the one field updated and leaves the original untouched. That's exactly what `.copy(...)` does. Two cards carrying the exact same details are treated as identical by anyone checking them, even though they're two separate physical cards — that's "structural equality," and you get it for free. The card also already lists all its fields clearly for anyone glancing at it — you never have to write your own "describe this card" logic.

**In this project:** `typesdemo/Bike.scala`. `Bike("Trek FX", 35)` is like printing a card. `bike.copy(topSpeed = 40)` prints a fresh card with one field changed, while the original `bike` card is untouched.

### Enum — a fixed multiple-choice menu

An **enum** is a **fixed menu** at a restaurant: you may order "Small," "Medium," or "Large" — and nothing else. There's no ordering an "Extra-Large" that was never on the menu. This closes the door on invented/typo'd values, so a checkout system can be certain it has covered every possible size. Each menu item can also carry its own fixed detail — "Medium" always costs $5 — the same way each `VehicleType` case always carries its own fixed wheel count.

**In this project:** `typesdemo/VehicleType.scala`. The only vehicle types that exist are `CarType`, `BikeType`, and `TruckType` — nothing else is possible — and each already knows its own wheel count (4, 2, and 6).

### Object — the one-and-only government office in town

An **object** is like the **one national passport office** — there's exactly one, everybody who needs a passport goes to that same one, and it springs into existence automatically the first time anyone actually needs it. You never "build a second one" the way you build many houses from a blueprint (a `class`) — there is only ever one `VehicleFactory` in the whole running program, and any running count it keeps (like how many vehicles it has built) is shared and visible to literally everyone who talks to it, anywhere in the code.

**In this project:** `typesdemo/VehicleFactory.scala`. No matter where in the code you call `VehicleFactory.buildCar(...)`, you're always talking to that same single office, and its running total is shared across every call. (`Car.scala` also shows a *companion* object — an office that happens to share its name with a blueprint and is allowed to peek at that blueprint's private details, mainly used to hand out ready-made houses via `Car(...)`.)

### File — a blank sheet of paper

The **File** option means "just give me an empty sheet of paper — don't make me pick a template." Scala doesn't actually require every file to hold exactly one class/trait/object; a file can hold several small, loose facts and recipes that don't belong to any single blueprint, certification, ID card, menu, or office. It's really just a folder for organizing code — the same way one page in a notebook can jot down several unrelated notes.

**In this project:** `typesdemo/Utils.scala`. A `formatReport` recipe and an `appVersion` fact sit here on their own, not tied to any one Car, Bike, or blueprint.

### Quick-reference table

| Real-world analogy | Scala construct | Can you make many? | Can it change after creation? |
|---|---|---|---|
| House blueprint | `class` | Yes — as many houses (instances) as you want | Yes — mutable fields allowed |
| Certification/skill | `trait` | Bolt onto any number of things | N/A — it's a skill, not a thing |
| Printed ID card | `case class` | Yes — but each "edit" prints a new card | No — immutable; `.copy()` makes a new one |
| Restaurant menu | `enum` | No — the list of choices is fixed forever | No — the set of cases is closed |
| National passport office | `object` | No — exactly one, ever | Its internal counters can change, but there's still only one office |
| Blank sheet of paper | `File` | N/A — just a container | N/A |

> **Key takeaway:** `class` = blueprint you build many of; `trait` = a bolt-on skill; `case class` = an immutable, auto-labeled data card; `enum` = a closed, fixed set of choices; `object` = a single shared instance that exists exactly once; `File` = a plain, template-free container that can hold any mix of the above.

## Learning Notes: IntelliJ File Icons

### Q: why did the file icons change from before? every file has now got the cylinder symbol

That red icon (the one that looks like a stacked cylinder) is IntelliJ's generic "Scala file" icon — the same one shown for **File** in the New Scala Class/File dialog, and the one `test.scala`/`Utils.scala`/`Runner.scala` already had.

**Why it changed:** IntelliJ only shows a specific icon (blue `C` for class, purple `E` for enum, orange `O` for object, etc.) when a file's *sole* top-level declaration is that one construct. Each file in `typesdemo` now has two top-level things in it — e.g. `Car.scala` has both `class Car`/`object Car` **and** the `@main def runCarDemo` added to make it individually runnable. Once a file mixes multiple top-level declarations like that, IntelliJ can no longer pick one type to represent it, so it falls back to the generic cylinder/file icon for all of them.

It's purely a Project-tree cosmetic thing — nothing about compilation or how the code runs is affected.

> **Key takeaway:** one top-level declaration per file → distinct type icon (Class/Trait/Enum/Object). Two or more top-level declarations in the same file (e.g. a type + an `@main def`) → IntelliJ falls back to the generic file icon. Cosmetic only, doesn't touch behavior. (This is exactly why `typesdemo2` — created without per-file `@main`s — kept its distinct icons, see below.)

## Learning Notes: Running Scala files with `sbt runMain`

### Q: `sbt "runMain typesdemo2.runTypesDemo2"` — explain this sentence so that i can run this type of files with different names

Breaking down `sbt "runMain typesdemo2.runTypesDemo2"`:

```
sbt   "runMain   typesdemo2 . runTypesDemo2"
 │         │         │            │
 │         │         │            └─ the @main def's own name (not the filename!)
 │         │         └─ the package (from the `package typesdemo2` line at the top of the file)
 │         └─ sbt's task that runs one specific entry point
 └─ the build tool CLI
```

**The general pattern:**
```bash
sbt "runMain <package>.<name-after-@main-def>"
```

**How to find the two pieces for any file:**

1. **Package** — look at the first line of the file: `package typesdemo2` → that's your package.
2. **Name** — look at the `@main def` line itself, e.g. `@main def runBikeDemo(): Unit = ...` → the name is `runBikeDemo`, **not** the filename `Bike.scala`. Scala 3 turns each `@main def foo` into its own hidden runnable class called `foo`, regardless of what file it lives in.

**Examples:**

| File | `@main def` line | Command |
|---|---|---|
| `typesdemo/Car.scala` | `@main def runCarDemo()` | `sbt "runMain typesdemo.runCarDemo"` |
| `typesdemo/Bike.scala` | `@main def runBikeDemo()` | `sbt "runMain typesdemo.runBikeDemo"` |
| `typesdemo/VehicleType.scala` | `@main def runVehicleTypeDemo()` | `sbt "runMain typesdemo.runVehicleTypeDemo"` |
| `typesdemo2/Runner.scala` | `@main def runTypesDemo2()` | `sbt "runMain typesdemo2.runTypesDemo2"` |

**Why the quotes:** the whole `runMain typesdemo2.runTypesDemo2` is one sbt command containing a space, so it's wrapped in quotes as a single shell argument — otherwise the shell would split it into two separate arguments and sbt wouldn't understand it.

**Shortcut:** if a package has only **one** `@main`/`main` entry point, plain `sbt run` (no quotes needed) works too and auto-picks it. `runMain` is only required when sbt has to disambiguate between multiple candidates.

> **Key takeaway:** the command is always `sbt "runMain <package>.<name-after-@main-def>"` — read the package off the file's `package` line and the name off the `@main def` line itself, never off the filename.

### Q: explain runMain in more detail, and its functionality and why not runMaster

**What `runMain` actually is:** a built-in sbt task (not a Scala/JVM concept) that does four things in sequence:

1. **Compiles** the project if anything changed
2. **Scans** the compiled `.class` files for anything with a runnable entry point — a `def main(args: Array[String]): Unit` (or, in Scala 3, the hidden object the compiler generates from an `@main def`)
3. **Picks** the exact one named on the command line instead of guessing
4. **Launches a JVM** (forked as a separate process by default in this project) and calls that entry point, passing any trailing words as `args: Array[String]`

So `sbt "runMain typesdemo.runCarDemo foo bar"` would run `runCarDemo` with `args(0) = "foo"`, `args(1) = "bar"`.

**`run` vs `runMain`:**

| | `run` | `runMain <fqcn>` |
|---|---|---|
| How it picks the entry point | Auto-detects. If there's exactly one, runs it. If there are several, it **prompts interactively** to pick a number | You tell it exactly which one, up front |
| Works in non-interactive/batch mode with multiple mains? | No — it can't prompt, so it fails or picks unpredictably | Yes — that's the whole point |

**Why it's called "Main," not "Master":** "Main" is inherited from Java/C's decades-old convention: every standalone program needs one designated entry-point method, historically `public static void main(String[] args)` — the method the JVM calls first when the program starts. "Master" isn't part of this vocabulary at all — there's no `runMaster` task in sbt, and no "master method" concept in Java/Scala. Elsewhere in dev tooling "master" usually refers to unrelated things (a git branch name, a primary/replica architecture term) — a coincidental word collision, not the same concept.

> **Key takeaway:** `runMain` lets you pick one exact entry point when a project has several `@main`/`main` methods, so it works safely in scripts/CI without needing to answer an interactive prompt; "Main" comes from Java's traditional program entry-point method and has nothing to do with "master" anything.

### Q: if i have to run typesdemo car and movable files give me the command

For the `typesdemo` package (the one with individual `@main`s per file):

```bash
sbt "runMain typesdemo.runCarDemo"
```

```bash
sbt "runMain typesdemo.runMovableDemo"
```

Run them one after another as two separate commands, or chain both into a single sbt session with `;`:

```bash
sbt "; runMain typesdemo.runCarDemo ; runMain typesdemo.runMovableDemo"
```

Each still refers to the `@main def` name (`runCarDemo`, `runMovableDemo`), not the filename (`Car.scala`, `Movable.scala`) — same rule as above.

> **Key takeaway:** one `sbt "runMain <package>.<name>"` call per entry point; chain several in one sbt session with `;` instead of launching sbt separately each time.
