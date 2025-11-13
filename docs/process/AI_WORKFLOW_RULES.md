# AI Workflow Rules for Code Changes

**Version:** 2.5 (Updated: 2025-11-13 - Added Mandatory Metadata Template)

### 📋 Version 2.5 Changes:
- **📝 ENHANCED:** Rule 2.5 - Added mandatory metadata block requirement
  - ALL new .md files must include metadata block at top
  - Standardized metadata template with required fields
  - Examples for Guide, Analysis, Process documents
  - Clear guidance on when to update metadata
  - Optional fields for versioning and cross-references
- **📚 DOCUMENTED:** Configuration README exceptions explicitly listed
  - Project root README.md
  - XML/README.md, Configurations/README.md, scripts/README.md
  - Principle: Feature-local docs allowed for specific components
- **✅ COMPLETE:** NAVIGATION.md now documents all .md file locations
- **🎯 GOAL:** All files have metadata within 3-6 months

### 📋 Version 2.4 Changes:
- **📁 ADDED:** Rule 2.5 - Markdown File Organization (ALL .md files in docs/)
  - Mandatory placement of all .md files in docs/ folder structure
  - Clear folder selection guide by purpose
  - Exceptions documented (.github/ISSUE_TEMPLATE/ only)
  - Verification command to check for misplaced files
  - Auto-fix process for mistakes
- **🔐 RENUMBERED:** Hardcoded Password rule moved to 2.6
- **✅ ORGANIZATION:** Created `docs/issues/open/` for issue templates
- **✅ MOVED:** Issue templates from `.github/` to `docs/issues/open/`
- **✅ UPDATED:** NAVIGATION.md to include issues/ folder

### 📋 Version 2.3 Changes:
- **🔐 ADDED:** Rule 2.5 - Hardcoded Passwords Strictly Forbidden
  - Zero tolerance policy for hardcoded passwords
  - Requires explicit user approval to use --no-verify
  - Clear process for handling pre-commit hook failures
  - Distinguishes between new violations (forbidden) and existing issues (require approval)
  - Documents when --no-verify is acceptable vs forbidden
  - Provides correct approach examples (Google Cloud Secret Manager, EPasswords enum)
- **✅ SECURITY:** Prevents accidental commit of hardcoded credentials
- **✅ PROCESS:** Clear approval workflow for existing password issues

### 📋 Version 2.2 Changes:
- **✅ OPTIMIZED:** CHANGE.log commit workflow - Reduced from 2 commits to 1!
  - Update previous entry's hash + add new entry with [PENDING] in single commit
  - Eliminates redundant "update hash" commits
  - Cleaner git history, half the pushes
- **✅ ADDED:** Documentation-only change detection - Skip build/test for docs!
  - **Local:** If ONLY changing .md, .log, .txt, or other doc files → skip compilation/tests
  - **Pipeline:** GitHub Actions auto-detects and skips build/test/deploy for doc-only changes
  - Saves 5-10 minutes locally, ~10-15 minutes in pipeline per doc commit
  - Git verification only
- **✅ OPTIMIZED:** GitHub Actions pipeline with smart change detection
  - New `detect-changes` job runs first
  - Skips build/compile/test/quality/deploy for documentation-only changes
  - Always shows pipeline summary (code vs doc-only)
  - Massive time savings on documentation commits!
- **✅ UPDATED:** Rule 4 with new single-commit workflow
- **✅ UPDATED:** Rule 3 with documentation-only change detection
- **✅ UPDATED:** Quick Reference with optimized steps
- **✅ UPDATED:** .github/workflows/ci.yml with smart skip logic

### 📋 Version 2.1 Changes:
- **✅ ADDED:** Mandatory file persistence verification step
  - Verify changes actually saved to files before proceeding
  - Command: `git status --short` to see modified files
  - Prevents "phantom fixes" that don't persist
- **✅ ADDED:** Example verification workflow
- **✅ UPDATED:** Quick Reference with persistence check

### 📋 Version 2.0 Changes:
- **✅ FIXED:** Docker config mount issues - Checkstyle now runs locally!
  - Added `checkstyle-custom.xml` and `checkstyle-suppressions.xml` volume mounts
  - Enables local Checkstyle validation before pushing
- **✅ ADDED:** Optional Checkstyle validation in Step 0 (post-format)
  - Command: `docker-compose run --rm tests checkstyle:checkstyle -DskipTests`
  - Shows violation count for monitoring progress
  - Non-blocking (BUILD SUCCESS even with violations)
- **✅ UPDATED:** Quick Reference with Checkstyle validation step
- **✅ REMOVED:** Outdated notes about Checkstyle config mount issues

---

## 🚨 MANDATORY RULES - NEVER SKIP THESE STEPS

### **Rule 1: Pre-Flight Verification (BEFORE any code changes)**
Before making ANY code changes, you MUST:
1. ✅ Check git status - ensure clean working tree
2. ✅ Verify Docker build: `docker-compose run --rm tests compile -DskipTests -Dcheckstyle.skip=true`
3. ✅ Run smoke tests: `docker-compose run --rm tests test -Dtest=SmokeTests -Dcheckstyle.skip=true`
4. ✅ Verify smoke tests pass (should see "Tests run: X, Failures: 0")

**STATUS:** Must see "BUILD SUCCESS" and smoke tests passing before proceeding.

**Note:** Full test suite will run on GitHub Actions. Smoke tests provide fast verification before starting work.

---

### **Rule 2: Batch Size Limits**
- **Maximum files per batch:** 10 files
- **Maximum fixes per batch:** 25 fixes
- **Reason:** Smaller batches = easier rollback if issues occur

---

### **Rule 2.5: Markdown File Organization** 📁

**ALL .md FILES MUST BE IN docs/ FOLDER STRUCTURE**

#### **Mandatory Placement:**
- ✅ **ALL** .md files must be created in the appropriate `docs/` subfolder
- ❌ **NEVER** create .md files in `.github/`, project root, or other locations
- ✅ Organize by purpose and lifecycle

#### **Folder Selection Guide:**

**Analysis & Recommendations** → `docs/analysis/YYYY-MM-DD-topic/`
- Code reviews
- Project audits
- Improvement recommendations
- Technical assessments

**How-To Guides** → `docs/guides/[infrastructure|testing|setup|troubleshooting]/`
- Setup instructions
- Usage guides
- Best practices
- Troubleshooting steps

**Team Processes** → `docs/process/`
- Team standards
- Workflow rules
- Code of conduct
- Contributing guidelines

**Architecture Decisions** → `docs/architecture/decisions/`
- Significant design decisions
- Technology choices
- Pattern selections
- Use ADR format

**Issue Templates** → `docs/issues/open/`
- Planned improvements
- Known bugs to fix
- Technical debt items
- Missing implementations

**Historical Work** → `docs/archive/YYYY-MM/`
- Completed work summaries
- Superseded documents
- Historical milestones

**Project Overview** → `docs/` (root only for major files)
- README.md
- NAVIGATION.md
- CHANGE.log
- REORGANIZATION_COMPLETE.md (transitional)

#### **Before Creating ANY .md File:**
1. Determine the file's purpose
2. Select appropriate folder from guide above
3. Create file in correct location
4. **Add metadata block at top** (MANDATORY - see template below)
5. Update NAVIGATION.md if adding new category
6. Add README.md to new folders

#### **Mandatory Metadata Block:**

**ALL new .md files MUST start with this metadata block:**

```markdown
---
**Type**: [Guide|Analysis|Process|ADR|Issue|Archive]
**Purpose**: [One-line description of what this document does]
**Created**: YYYY-MM-DD
**Last Updated**: YYYY-MM-DD
**Maintained By**: [CJS QA Team|AI Code Analysis System|Your Name]
**Status**: [Active|Draft|Archived|Superseded|Deprecated]
---

# Document Title

Content starts here...
```

**Required Fields**:
- **Type**: Document category (Guide, Analysis, Process, ADR, Issue, Archive)
- **Purpose**: One-line description
- **Created**: Initial creation date (YYYY-MM-DD)
- **Last Updated**: Most recent change date (update when editing)
- **Maintained By**: Who keeps this current
- **Status**: Current state (Active, Draft, Archived, Superseded, Deprecated)

**Optional Fields** (add if relevant):
- **Version**: For versioned docs (e.g., v2.4)
- **Related To**: Links to related documents
- **Supersedes**: If replacing an older document
- **Superseded By**: If this doc is outdated

**When to Update**:
- **Created**: Set once, never change
- **Last Updated**: Update EVERY time you edit the file
- **Status**: Update if document state changes
- **Version**: Increment based on change significance

**Example**:
```markdown
---
**Type**: Guide
**Purpose**: Complete setup guide for Docker and Selenium Grid infrastructure
**Created**: 2025-11-08
**Last Updated**: 2025-11-12
**Maintained By**: CJS QA Team
**Status**: Active
---

# Docker & Selenium Grid Setup Guide
```

#### **Common Mistakes to Avoid:**
- ❌ Creating issue templates in `.github/` (use `docs/issues/open/`)
- ❌ Creating guides in project root (use `docs/guides/`)
- ❌ Creating analysis in project root (use `docs/analysis/YYYY-MM-DD-topic/`)
- ❌ Forgetting to update NAVIGATION.md

#### **Exceptions - .md Files Outside docs/:**

**ONLY these .md files are allowed outside docs/ folder:**

**GitHub UI Templates** (Required by GitHub):
- ✅ `.github/ISSUE_TEMPLATE/bug_report.md`
- ✅ `.github/ISSUE_TEMPLATE/feature_request.md`
- ✅ `.github/ISSUE_TEMPLATE/test_failure.md`
- ✅ `.github/pull_request_template.md`

**Reason**: GitHub automatically recognizes these specific locations for its UI. Moving them would break the template functionality.

**Project & Configuration READMEs** (Feature-Local Documentation):
- ✅ `README.md` (project root - GitHub landing page)
- ✅ `XML/README.md` (XML configuration guide)
- ✅ `Configurations/README.md` (environment setup guide)
- ✅ `scripts/README.md` (script usage guide)

**Reason**: These document specific features/directories and should live with those features.

**Principle**: Documentation can be **feature-local** when it only pertains to that specific component. The `docs/` folder is for **framework-level** and **cross-cutting** documentation.

**All Other .md Files** → Must be in `docs/` structure ❌

#### **Verification:**
```bash
# Check for UNEXPECTED .md files outside docs/
find . -name "*.md" \
  -not -path "./docs/*" \
  -not -path "./.github/ISSUE_TEMPLATE/*" \
  -not -path "./.github/pull_request_template.md" \
  -not -path "./README.md" \
  -not -path "./XML/README.md" \
  -not -path "./Configurations/README.md" \
  -not -path "./scripts/README.md" \
  -not -path "./node_modules/*" \
  -not -path "./target/*"

# Should show NO output (all .md files in correct locations)
# If any files show up → they need to be moved to docs/
```

**Expected .md file locations**:
```bash
# Framework documentation (docs/):
docs/**/*.md                              ✅ Primary location

# GitHub UI templates:
.github/ISSUE_TEMPLATE/*.md               ✅ Exception (GitHub UI)
.github/pull_request_template.md          ✅ Exception (GitHub UI)

# Feature-local documentation:
README.md                                  ✅ Exception (Project root)
XML/README.md                              ✅ Exception (Config guide)
Configurations/README.md                   ✅ Exception (Config guide)
scripts/README.md                          ✅ Exception (Script guide)

# Everything else:
*.md anywhere else                         ❌ Move to docs/
```

#### **Auto-Fix Process:**
If you create a .md file in the wrong location:
1. STOP immediately when you realize
2. Move file to appropriate docs/ subfolder
3. Update any references/links
4. Update NAVIGATION.md if needed
5. Commit the correction

**Remember**: Well-organized documentation is as important as well-organized code! 📚

---

### **Rule 2.6: Hardcoded Passwords - STRICTLY FORBIDDEN** 🔐

**🚨 CRITICAL SECURITY RULE - NEVER BYPASS WITHOUT EXPLICIT USER APPROVAL**

#### **Absolute Prohibition:**
- ❌ **NEVER add hardcoded passwords to any file**
- ❌ **NEVER commit code with hardcoded passwords**
- ❌ **NEVER use `--no-verify` to bypass pre-commit password checks WITHOUT user approval**

#### **What Counts as a Hardcoded Password:**
- Any password in plain text in source code
- API keys, tokens, secrets in code
- Database credentials in configuration files
- Service account passwords
- Test account passwords (unless explicitly marked as dummy/fake)

#### **Pre-Commit Hook Detection:**
The pre-commit hook checks for hardcoded passwords using pattern matching:
```bash
# The hook will FAIL if it finds patterns like:
password = "actual_value"
password: "actual_value"
pwd = "actual_value"
secret = "actual_value"
token = "actual_value"
```

#### **Required Process:**

**1. During Development:**
- ✅ Use Google Cloud Secret Manager for ALL credentials
- ✅ Use `EPasswords` enum for password retrieval
- ✅ Use environment variables for test credentials
- ✅ Use `.xml.template` files with placeholders

**2. Before Commit:**
```bash
# Check for hardcoded passwords (pre-commit hook will run automatically)
git add -A
git commit -m "..."

# If pre-commit hook FAILS with "Possible hardcoded password found!":
```

**3. If Pre-Commit Hook Fails:**

**Option A: Fix the Issue (REQUIRED if YOU added the password)**
```bash
# 1. Review the output - which files have passwords?
# 2. If YOU added these passwords in THIS batch:
#    - STOP immediately
#    - Remove the hardcoded passwords
#    - Use Google Cloud Secret Manager instead
#    - Update code to retrieve from EPasswords enum
#    - Re-run commit
```

**Option B: Request User Approval (ONLY for existing passwords)**
```bash
# If the passwords were ALREADY in the repository (not added by you):
# 1. STOP and notify the user
# 2. Show them the pre-commit hook output
# 3. Explain:
#    - "Pre-commit hook detected hardcoded passwords in existing files"
#    - "These are NOT new changes in this commit"
#    - List the files with passwords
# 4. Ask: "May I use --no-verify to bypass the check for this commit?"
# 5. WAIT for explicit user approval
# 6. Only proceed with --no-verify if user approves
```

**4. User Approval Required:**
```
AI: "⚠️  Pre-commit hook detected hardcoded passwords in these files:
     - src/test/java/com/cjs/qa/pluralsight/pages/LoginPage.java
     - src/test/java/com/cjs/qa/gt/Notes.txt

     These are existing passwords (not added in this commit).

     May I use --no-verify to bypass the check for this commit?

     (Note: These existing passwords should be migrated to Google Cloud
     Secret Manager in a future cleanup task.)"

User: "Yes, approved" OR "No, let's fix them first"
```

#### **When --no-verify is ACCEPTABLE:**
✅ Only when ALL of these conditions are met:
1. User has given EXPLICIT approval
2. The passwords are EXISTING (not added by you)
3. The passwords are NOT related to your current changes
4. You document the bypass in the commit message
5. You note that cleanup is needed in the future

#### **When --no-verify is FORBIDDEN:**
❌ NEVER use --no-verify if:
1. YOU added the hardcoded password
2. User has not approved
3. The password is related to your changes
4. It's a new security issue

#### **Documentation Required:**
If user approves --no-verify, you MUST:
1. Add note to commit message:
   ```
   NOTE: Used --no-verify because pre-commit hook flagged existing
   hardcoded passwords in unmodified files (not new changes).
   User approval obtained. These are known existing issues to be
   addressed in future cleanup.
   ```
2. Add entry to CHANGE.log explaining the situation
3. Consider creating a GitHub issue to track password cleanup

#### **Zero Tolerance Policy:**
- Any attempt to sneak in hardcoded passwords = IMMEDIATE STOP
- Any bypass of password checks without approval = VIOLATION
- Security is NON-NEGOTIABLE

#### **Correct Approach - Example:**
```java
// ❌ WRONG - Hardcoded password
String password = "MySecretP@ssw0rd";

// ✅ CORRECT - Retrieved from Google Cloud Secret Manager
String password = EPasswords.MY_SERVICE.getValue();

// ✅ CORRECT - Environment variable
String password = System.getenv("MY_SERVICE_PASSWORD");

// ✅ CORRECT - Configuration file (not committed to git)
String password = config.getPassword("my-service");
```

**Remember:** This project has ZERO hardcoded passwords in active code. Let's keep it that way! 🔐

---

### **Rule 3: Post-Change Verification (AFTER each batch)**

**FIRST: Check if documentation-only change:**
```bash
git status --short | grep -v -E '\.(md|log|txt|rst|adoc)$'
```
- **If NO output:** Only doc files changed → Skip to Rule 4 (no build/test needed!) ✅
- **If output shows .java/.xml/etc:** Code/config changed → Run full verification below

**Documentation-Only Files (skip build/test):**
- `.md` files (README, CHANGE.log, rules, etc.)
- `.log` files
- `.txt` files
- `.rst`, `.adoc` files (other documentation formats)
- **Verification needed:** Git commands only (status, commit, push)

---

After making **code changes**, you MUST verify in this order:

#### **Step 0: Google Java Format + Checkstyle Validation (Auto-fix & validate)**
```bash
# Run Google Java Format to auto-fix import and style issues
docker-compose run --rm tests com.spotify.fmt:fmt-maven-plugin:format -Dcheckstyle.skip=true

# (Optional but recommended) Run Checkstyle to catch remaining style issues
docker-compose run --rm tests checkstyle:checkstyle -DskipTests
```
**Purpose:**
- Auto-fixes redundant/unused imports
- Ensures consistent code formatting
- Validates against Checkstyle rules (optional)
- Should run BEFORE compilation to catch any formatting-introduced issues

**Checkstyle Output:**
- Shows violation count: "You have X Checkstyle violations"
- BUILD SUCCESS even with violations (failOnViolation=false)
- Use this to monitor progress, not as a gate

**Duration:** ~30-60 seconds (format), ~20-30 seconds (checkstyle)

#### **Step 0b: Verify File Changes Persist (CRITICAL!)**
```bash
# After making ANY code changes, verify they actually saved:
git status --short

# You should see:
#  M src/test/java/com/cjs/qa/some/File.java
#  M src/test/java/com/cjs/qa/other/File.java
# ... etc.

# ⚠️  If you DON'T see modified files:
# - Changes didn't persist (volume mount issue or editor problem)
# - DO NOT proceed - investigate why changes didn't save
# - Re-apply changes and verify again
```
**Required:** Modified files appear in `git status`

**CRITICAL:** If changes don't appear:
- Changes are NOT saved to filesystem
- Compilation will use old code
- Pushing will NOT include your fixes
- **MUST** re-apply and verify before proceeding

**Example Workflow:**
1. Make 10 file changes
2. Run `git status --short`
3. Verify 10 files show as modified
4. If count doesn't match → investigate and fix
5. Only proceed when all expected files are modified

**Duration:** ~5 seconds

#### **Step 1: Compilation Verification (MUST MATCH PIPELINE!)**
```bash
# ✅ REQUIRED COMMAND (matches pipeline exactly):
docker-compose run --rm tests compile test-compile

# Alternative with clean (if needed):
docker-compose run --rm tests clean compile test-compile

# ❌ WRONG: docker-compose run --rm tests compile -DskipTests
#    (This skips test-compile phase! Will miss test compilation errors!)
```
**Required:** BUILD SUCCESS

**CRITICAL:** Pipeline runs `./mvnw clean compile test-compile`
- **You MUST run:** `compile test-compile` (NOT just `compile`)
- **Why:** `compile` only compiles src/main/java
- **test-compile** compiles src/test/java (where most code is!)
- Missing `test-compile` = test compilation errors reach pipeline!

#### **Step 2: Smoke Tests (Fast)**
```bash
docker-compose run --rm tests test -Dtest=SmokeTests -Dcheckstyle.skip=true
```
**Required:** All smoke tests pass (Tests run: X, Failures: 0)
**Duration:** ~2-3 minutes (much faster than full suite)
**Purpose:** Catch compilation and basic runtime issues before pushing
**Alternative (if local Maven works):** `mvn test -Dtest=SmokeTests -Dcheckstyle.skip=true`

#### **Step 3: Docker Build Test (Every 3-5 batches)**
```bash
docker-compose build tests
```
**Required:** Image builds successfully
**Frequency:** Every 3-5 batches (not every single batch)

#### **Step 4: Full Test Suite (Checkpoints)**
```bash
docker-compose run --rm tests test -Dcheckstyle.skip=true
```
**Required:** Run every 5-10 batches as checkpoint
**Frequency:** Periodic checkpoints, not every batch
**Duration:** ~10-15 minutes

**❌ CRITICAL:** If ANY verification fails, you MUST:
1. DO NOT push the changes
2. Identify the root cause
3. Fix the issue
4. Re-run ALL verification steps
5. Proceed only after all verifications pass

---

### **Rule 4: Commit & Push Process (OPTIMIZED - Single Commit)**
Only after ALL verifications pass:

1. ✅ **Update CHANGE.log** (MANDATORY BEFORE COMMIT)
   - **FIRST:** Get actual current timestamp: `date "+%Y-%m-%d %H:%M:%S"` (CST timezone)
   - **SECOND:** Get last commit hash: `git log -1 --format=%h`
   - Update CHANGE.log with BOTH:
     a) **Update previous entry:** Find last `[PENDING]` and replace with actual commit hash
     b) **Add new entry** at top of file with:
        - **Timestamp:** `[YYYY-MM-DD HH:MM:SS CST]` - **MUST use actual system time from date command**
        - Commit hash: `[PENDING]` (will be updated in next batch)
        - Summary title
        - Overview section with high-level summary
        - **Cursor Token Status:** `Tokens Used: X / Total: Y (Z remaining, A% used, B% remaining)`
        - Detailed changes, files modified, verification results
        - Impact summary and next steps
   - Format: Follow existing CHANGE.log structure
   - Content: Include all changes since last entry
   - **Token tracking:** Always include current token usage with percentages for session visibility
   - **CRITICAL:** Never guess timestamps - always use actual system time!
   - **BENEFIT:** Updates previous hash + adds new entry = 1 commit instead of 2!

2. ✅ Stage changes: `git add -A` (including docs/CHANGE.log with both updates)
3. ✅ Commit with descriptive message following established format
4. ✅ Push to GitHub: `git push origin main`
5. ✅ Verify push succeeded
6. ✅ Monitor GitHub Actions status
7. ✅ If GitHub Actions fails, STOP and fix before next batch

**Note:** Last entry of session will have `[PENDING]` - gets updated in next session or can be updated at end of session if desired.

**GitHub Actions Monitoring:**
- Check https://github.com/CScharer/selenium_java_docker/actions after push
- Wait for build to complete before next batch (or proceed if user approves)
- If any job fails, investigate and fix before continuing

---

### **Rule 5: Error Handling**
If you encounter ANY error:

1. **Compilation Error:**
   - DO NOT push
   - Read the full error message
   - Identify the affected file(s)
   - Fix the error
   - Re-run compilation
   - Proceed only after BUILD SUCCESS

2. **Test Failure:**
   - Identify if it's a known flaky test (testWindowManagement, ResponsiveDesignTests)
   - If NEW failure: DO NOT push
   - If known flaky: Document and proceed (if 65+/66 pass)

3. **Docker Build Failure:**
   - DO NOT push
   - Read the full error log
   - Fix the issue
   - Re-run Docker build
   - Proceed only after successful build

4. **Network/Transient Errors:**
   - Retry the command once
   - If persists, document and notify user
   - DO NOT push code changes if verification incomplete

---

### **Rule 6: Documentation**
- ✅ Update CHANGE.log at END of session (or after major milestones)
- ✅ Use descriptive commit messages
- ✅ Document patterns fixed, files modified, verification results
- ✅ Include commit hashes for traceability

---

### **Rule 7: Rollback Plan**
If you pushed code that breaks the build:

1. Immediately notify user
2. Identify the breaking commit: `git log --oneline -5`
3. Propose rollback: `git revert <commit-hash>` OR fix forward
4. Wait for user approval before proceeding

---

## 📋 VERIFICATION CHECKLIST TEMPLATE

Use this checklist for EVERY batch:

```
### Batch N: [Description]

**Files Modified:** [count] files
**Fixes Applied:** [count] fixes

**Pre-Push Verification:**
[ ] Step 1: Compilation - BUILD SUCCESS
[ ] Step 2: Tests - 65+/66 passing
[ ] Step 3: Docker Build - Image built successfully

**Push Status:**
[ ] Changes committed
[ ] Pushed to GitHub
[ ] Verified push succeeded

**Post-Push:**
[ ] No errors in git push
[ ] Ready for next batch OR end of session
```

---

## 🎯 EXCEPTION HANDLING

### **Known Flaky Tests (Allowed to Fail):**
- `testWindowManagement` (timing-sensitive)
- `ResponsiveDesignTests.tearDown` (cleanup race condition)

**Rule:** If 65 or 66 tests pass, proceed. If fewer than 65 pass, STOP and investigate.

### **Network Errors in Docker:**
If you see dependency download errors:
1. Retry once
2. If persists, proceed with compilation-only verification
3. Note in commit message: "Tests deferred to GitHub Actions (Docker network issue)"
4. Inform user to check GitHub Actions for test results

---

## ✅ QUICK REFERENCE

**Before EVERY batch:**
```bash
# 0. Run Google Java Format (auto-fix imports/formatting, ~30-60 sec)
docker-compose run --rm tests com.spotify.fmt:fmt-maven-plugin:format -Dcheckstyle.skip=true

# 0b. (Optional) Run Checkstyle validation to monitor violations (~20-30 sec)
docker-compose run --rm tests checkstyle:checkstyle -DskipTests

# 0c. VERIFY FILE CHANGES PERSIST (CRITICAL - 5 sec)
git status --short
# ⚠️  Count modified files - should match number of files you changed!
# If files missing, changes didn't persist - STOP and investigate!

# 1. Verify compilation (REQUIRED - every batch, MUST INCLUDE test-compile!)
docker-compose run --rm tests compile test-compile
# OR with clean: docker-compose run --rm tests clean compile test-compile
# ❌ WRONG: docker-compose run --rm tests compile -DskipTests (misses test-compile!)

# 2. Run smoke tests (REQUIRED - every batch, fast ~2-3 min)
docker-compose run --rm tests test -Dtest=SmokeTests -Dcheckstyle.skip=true

# 3. Docker build (every 3-5 batches)
docker-compose build tests

# 4. Full test suite (every 5-10 batches as checkpoint)
docker-compose run --rm tests test -Dcheckstyle.skip=true
```

**After verification passes (or if documentation-only):**
```bash
# 5. Check if documentation-only change (NEW in v2.2!)
git status --short | grep -v -E '\.(md|log|txt|rst|adoc)$'
# If NO output → Documentation-only! Skip steps 1-4 above ✅

# 6. Get actual timestamp (FIRST!)
date "+%Y-%m-%d %H:%M:%S"

# 7. Get last commit hash (SECOND!)
git log -1 --format=%h

# 8. Update CHANGE.log (MANDATORY - BOTH updates in one!)
# a) Find previous [PENDING] entry → replace with actual commit hash
# b) Add NEW entry at top with [PENDING], timestamp, summary, details

# 9. Commit and push (SINGLE commit with both updates!)
git add -A
git commit -m "..."
# NOTE: If pre-commit hook fails with password warning:
# - Review the output carefully
# - If YOU added passwords: STOP and fix them!
# - If passwords already exist: Request user approval before using --no-verify
git push origin main

# 10. Monitor GitHub Actions (if code changed)
# Check https://github.com/CScharer/selenium_java_docker/actions
# Wait for green status or investigate failures
# (Documentation-only changes: pipeline will skip tests automatically)
```

**Benefits of v2.2:**
- ✅ 1 commit per batch (was 2)
- ✅ 1 push per batch (was 2)
- ✅ Cleaner git history
- ✅ More efficient workflow

**NEVER skip:**
- Compilation + smoke tests (unless explicitly approved by user)
- CHANGE.log update (MANDATORY before every push)
- Hardcoded password check (NEVER use --no-verify without user approval)

---

## 🎯 SMOKE TESTS

**What are smoke tests?**
- Basic functionality tests that run quickly
- Located in: `src/test/java/com/cjs/qa/junit/tests/SmokeTests.java`
- Cover: Basic navigation, element finding, driver initialization
- Duration: ~2-3 minutes (vs 10-15 min for full suite)

**Why smoke tests?**
- Fast feedback on compilation AND runtime issues
- Catch most regressions without full test suite wait
- ~5x faster than full suite
- Complement comprehensive GitHub Actions testing

**Command:**
```bash
docker-compose run --rm tests test -Dtest=SmokeTests -Dcheckstyle.skip=true
```

**Alternative (if local Maven environment is properly configured):**
```bash
mvn test -Dtest=SmokeTests -Dcheckstyle.skip=true
```

**Expected Output:**
```
Tests run: X, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

**Note:** Using Docker ensures consistent environment regardless of local Maven setup.

---

## 🚫 EXCLUDED TESTS - NEVER RUN AUTOMATICALLY

### **Load & Performance Tests - MANUAL ONLY**

**❌ DO NOT run these tests during normal workflow:**

**Tools to EXCLUDE:**
1. **Gatling** (Scala-based load testing)
   - Files: `src/test/scala/*LoadSimulation.scala`
   - Reason: Resource-intensive, generates load on systems

2. **Locust** (Python-based load testing)
   - Files: `src/test/locust/*_test.py`
   - Reason: Generates HTTP load, requires specific configuration

3. **JMeter** (Performance testing)
   - Files: `src/test/jmeter/*.jmx`
   - Reason: Performance benchmarking, not functional testing

**When to run these tests:**
- ✅ Only when explicitly requested by user
- ✅ Manual performance benchmarking sessions
- ✅ Before major releases (scheduled/planned)
- ❌ NEVER during routine code changes
- ❌ NEVER during PMD fixes or refactoring

**Why exclude them?**
- Generate significant load on systems/services
- Require specific infrastructure setup
- Take considerable time to complete
- Not relevant for code quality verification
- May impact production/staging environments if misconfigured

**Maven profiles (if any exist) to AVOID:**
- `-P performance`
- `-P load-test`
- `-P gatling`
- `-P jmeter`

**If user requests load/performance testing:**
1. Confirm the request explicitly
2. Verify infrastructure is ready
3. Run in isolated environment
4. Monitor resource usage
5. Document results separately

---

## 📝 NOTES

- These rules apply to ALL code changes, not just PMD fixes
- User may override these rules on a case-by-case basis
- When in doubt, ask the user before proceeding
- Prioritize code quality over speed

---

**Last Updated:** 2025-11-12 01:59:00 CST
**Version:** 1.0
**Applies To:** All AI-driven code changes in this repository
