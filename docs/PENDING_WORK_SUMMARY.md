# 📋 Pending Work Summary

**Last Updated**: 2025-11-14  
**Status**: Active tracking of remaining tasks

---

## 🔴 HIGH PRIORITY - Open Issues

### 1. API Keys Migration - User Action Required ⏳
**Status**: Code complete, awaiting Secret Manager setup  
**Location**: `docs/issues/open/hardcoded-api-keys-and-secrets.md`

**What's Done:**
- ✅ `EAPIKeys` enum created with VIVIT_ prefix
- ✅ All code updated to use enum getters
- ✅ Migration guide created
- ✅ Code merged to main

**What's Pending:**
- ⏳ **User must create 6 secrets in Google Cloud Secret Manager**
- ⏳ Test secret retrieval after creation
- ⏳ Remove deprecated constants after successful testing

**Secrets to Create:**
1. `AUTO_VIVIT_GT_WEBINAR_USER_ID` = "jill.vivit@yahoo.com"
2. `AUTO_VIVIT_GT_WEBINAR_PASSWORD` = "vivitrules1"
3. `AUTO_VIVIT_GT_WEBINAR_CONSUMER_KEY` = "WGhbDnxCGUwKNABGKeymjoII4gqalCa3"
4. `AUTO_VIVIT_GT_WEBINAR_CONSUMER_SECRET` = "DdkRQTJGLq4VF20t"
5. `AUTO_VIVIT_YM_API_KEY` = "1879E438-29E0-41C2-AFAD-3E11A84BBEF7"
6. `AUTO_VIVIT_YM_API_SA_PASSCODE` = "HNe6RO84P5sI"

**Guide**: `docs/guides/setup/API_KEYS_MIGRATION_GUIDE.md`

---

### 2. Cleanup Remaining Hardcoded Passwords 🔐
**Status**: Open issue  
**Location**: `docs/issues/open/cleanup-hardcoded-passwords.md`  
**Priority**: Medium-High

**5 Files Status (Verified 2025-11-14):**

1. ✅ **`src/test/java/com/cjs/qa/gt/api/namespace/webinar/AuthNamespace.java`**
   - **FIXED**: Now uses `getPassword()` from enum
   - Status: Resolved in API keys migration

2. ⚠️ **`src/test/java/com/cjs/qa/pluralsight/pages/LoginPage.java`**
   - Password: `"C@Training"` (marked as "DUMMY PASSWORD - For testing/training only")
   - Action: Verify if this is truly a dummy/test password or needs migration
   - Priority: Low (if dummy, may be acceptable)

3. ⚠️ **`src/test/java/com/cjs/qa/gt/Notes.txt`**
   - Contains API credentials in curl examples
   - Action: Replace with placeholders `[PASSWORD]` or migrate if active
   - Note: Some credentials may have been migrated (verify)

4. ✅ **`src/test/java/com/cjs/qa/junit/tests/ScenariosTests.java`**
   - Empty password: `String password = "";`
   - **Status**: Intentional - comment says "Empty password is intentional - will be set from EPasswords enum below"
   - Action: None needed (intentional test case)

5. ⚠️ **`src/test/java/com/cjs/qa/atlassian/Atlassian.java`**
   - Placeholder password: `"********"`
   - Action: Verify if this is a placeholder or needs actual migration
   - Priority: Low (if placeholder, may be acceptable)

---

### 3. Missing Performance Test Files 🚨
**Status**: CI/CD pipeline failing  
**Location**: `docs/issues/open/missing-performance-test-files.md`  
**Priority**: High (but workflow is disabled)

**Problem**: Performance workflow references files that don't exist:
- ❌ `src/test/locust/api_load_test.py`
- ❌ `src/test/locust/web_load_test.py`
- ❌ `src/test/locust/comprehensive_load_test.py`
- ❌ Scala Gatling files
- ❌ JMeter JMX files

**Current Status**: Performance workflow is disabled (scheduled runs disabled)

**Options:**
1. Create the missing test files
2. Update workflow to skip if files don't exist
3. Remove performance workflow until ready

---

## 🟡 MEDIUM PRIORITY - Quick Action Plan Items

### From `docs/analysis/2025-11-13-comprehensive/QUICK_ACTION_PLAN.md`:

#### ✅ COMPLETED:
- ✅ **Option 1**: Quick Wins (Docker ignore, Dependabot, etc.)
- ✅ **Option 4**: Update Maven Plugins
- ✅ **Option C**: Page Object Generator (implemented and tested)

#### ⏳ REMAINING HIGH PRIORITY:

**1. Remove Dangerous Dependencies** (4-6 hours)
- Remove: `phantomjsdriver`, `log4j 1.2.17`, `commons-lang 2.6`, `axis 1.4`
- **Status**: Previously attempted, some may still exist
- **Action**: Verify current state and complete removal

**2. Fix Logging Inconsistency** (8-12 hours)
- Standardize on Log4j 2 + SLF4J
- Replace `System.out.println` and `Environment.sysOut`
- **Status**: Partially done (logging standardization branch was merged)
- **Action**: Verify completion, check for remaining inconsistencies

**3. Add Data-Driven Testing** (12-16 hours)
- Create Excel/JSON/CSV data providers
- Implement `ExcelDataProvider.java`
- **Status**: Not started
- **Action**: Plan and implement

**4. Add API Contract Testing** (8-12 hours)
- Create JSON schemas for APIs
- Add schema validation to API tests
- **Status**: Not started
- **Action**: Plan and implement

---

## 🟢 LOW PRIORITY - Future Enhancements

### From Quick Action Plan (Week 5-12):
- Visual Regression Testing
- Test Retry Logic
- Migrate to Java 17 Features
- Optimize Docker images
- Enhance CI/CD pipeline
- Add test trending
- Create ADRs
- Record video tutorials
- Write troubleshooting guide

---

## 📊 Summary

### Immediate Actions Needed:
1. ⏳ **User**: Create 6 secrets in Google Cloud Secret Manager
2. 🔍 **Verify**: Check if remaining password files were addressed
3. 🚨 **Decide**: What to do with missing performance test files

### Next Sprint Items:
1. Complete dangerous dependencies removal
2. Verify logging standardization is complete
3. Plan data-driven testing implementation
4. Plan API contract testing implementation

### Completed Recently:
- ✅ API Keys migration (code complete)
- ✅ Page Object Generator
- ✅ Firefox tests disabled
- ✅ Logging standardization (merged)
- ✅ Dangerous dependencies removal (merged)

---

## 🎯 Recommended Next Steps

**This Week:**
1. Create API secrets in Google Cloud (when ready to test)
2. Verify remaining password files status
3. Review and prioritize Quick Action Plan items

**This Month:**
1. Complete dangerous dependencies removal
2. Verify logging standardization completion
3. Start data-driven testing implementation

---

**Questions?** Check individual issue documents in `docs/issues/open/` for detailed information.

