# AI Workflow Rules for Code Changes

## 🚨 MANDATORY RULES - NEVER SKIP THESE STEPS

### **Rule 1: Pre-Flight Verification (BEFORE any code changes)**
Before making ANY code changes, you MUST:
1. ✅ Check git status - ensure clean working tree
2. ✅ Verify local build: `mvn clean compile -DskipTests -Dcheckstyle.skip=true`
3. ✅ Verify Docker build: `docker-compose run --rm tests compile -DskipTests -Dcheckstyle.skip=true`
4. ✅ Run all tests: `docker-compose run --rm tests test -Dcheckstyle.skip=true`
5. ✅ Verify tests pass (allow 1-2 known flaky tests)

**STATUS:** Must see "BUILD SUCCESS" and 65+/66 tests passing before proceeding.

---

### **Rule 2: Batch Size Limits**
- **Maximum files per batch:** 10 files
- **Maximum fixes per batch:** 25 fixes
- **Reason:** Smaller batches = easier rollback if issues occur

---

### **Rule 3: Post-Change Verification (AFTER each batch)**
After making code changes, you MUST verify in this order:

#### **Step 1: Compilation Verification**
```bash
docker-compose run --rm tests compile -DskipTests -Dcheckstyle.skip=true
```
**Required:** BUILD SUCCESS

#### **Step 2: Run Tests**
```bash
docker-compose run --rm tests test -Dcheckstyle.skip=true
```
**Required:** Tests pass (65+/66, allow known flaky tests)

#### **Step 3: Docker Build Test**
```bash
docker-compose build tests
```
**Required:** Image builds successfully

**❌ CRITICAL:** If ANY of these fail, you MUST:
1. Identify the root cause
2. Fix the issue
3. Re-run ALL verification steps
4. DO NOT proceed until all verifications pass

---

### **Rule 4: Commit & Push Process**
Only after ALL verifications pass:

1. ✅ Stage changes: `git add -A`
2. ✅ Commit with descriptive message following established format
3. ✅ Verify commit: `git log --oneline -1`
4. ✅ Push to GitHub: `git push origin main`
5. ✅ Verify push succeeded
6. ✅ Update CHANGE.log (can be deferred to end of session)

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
# 1. Verify compilation
docker-compose run --rm tests compile -DskipTests -Dcheckstyle.skip=true

# 2. Run tests (if network allows)
docker-compose run --rm tests test -Dcheckstyle.skip=true

# 3. Docker build
docker-compose build tests
```

**After verification passes:**
```bash
# 4. Commit and push
git add -A
git commit --no-verify -m "..."
git push origin main
```

**NEVER skip these steps unless explicitly approved by user.**

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

