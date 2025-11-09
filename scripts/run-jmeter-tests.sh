#!/bin/bash
# JMeter Performance Test Runner
# 30% of performance testing allocation

set -e

echo "📊 Running JMeter Performance Tests..."
echo "=========================================="

# Configuration
JMETER_VERSION="5.6.3"
TEST_DIR="src/test/jmeter"
RESULTS_DIR="target/jmeter"

echo "🎯 Apache JMeter Load Testing"
echo "   - Industry-standard performance tool"
echo "   - Comprehensive protocols"
echo "   - Detailed reports"
echo ""

# Check if JMeter test files exist
if [ ! -d "$TEST_DIR" ]; then
    echo "❌ No JMeter test directory found"
    echo "   Expected: $TEST_DIR"
    exit 1
fi

echo "📋 Available Test Plans:"
find $TEST_DIR -name "*.jmx" -exec basename {} \; | sed 's/^/   - /'
echo ""

# Clean previous results
echo "🧹 Cleaning previous results..."
rm -rf $RESULTS_DIR/results
rm -rf $RESULTS_DIR/reports
mkdir -p $RESULTS_DIR/results
mkdir -p $RESULTS_DIR/reports

# Run JMeter tests via Maven
echo "🚀 Executing JMeter test plans..."
echo ""

./mvnw jmeter:jmeter jmeter:results

JMETER_EXIT_CODE=$?

echo ""
echo "=========================================="
if [ $JMETER_EXIT_CODE -eq 0 ]; then
    echo "✅ JMeter Tests COMPLETED"
else
    echo "⚠️  JMeter Tests completed with errors"
fi
echo "=========================================="

# Display results
echo ""
echo "📊 JMeter Results:"
if [ -d "$RESULTS_DIR/reports" ]; then
    echo "   Reports directory: $RESULTS_DIR/reports"
    
    # Check for HTML dashboard
    if [ -f "$RESULTS_DIR/reports/index.html" ]; then
        echo "   Dashboard: $RESULTS_DIR/reports/index.html"
        echo ""
        echo "💡 Open in browser:"
        echo "   open $RESULTS_DIR/reports/index.html  (macOS)"
        echo "   xdg-open $RESULTS_DIR/reports/index.html  (Linux)"
        echo ""
        
        # Auto-open on macOS
        if [[ "$OSTYPE" == "darwin"* ]]; then
            read -p "Open dashboard now? (y/n) " -n 1 -r
            echo
            if [[ $REPLY =~ ^[Yy]$ ]]; then
                open "$RESULTS_DIR/reports/index.html"
            fi
        fi
    fi
else
    echo "   No reports generated"
fi

echo ""
echo "📈 Performance Metrics:"
echo "   - Response time statistics"
echo "   - Throughput (requests/sec)"
echo "   - Error percentage"
echo "   - Latency graphs"
echo "   - Transaction reports"

echo ""
echo "💡 View detailed results:"
echo "   - HTML reports: $RESULTS_DIR/reports/"
echo "   - CSV data: $RESULTS_DIR/results/"
echo "   - JTL files: $RESULTS_DIR/results/*.jtl"

exit $JMETER_EXIT_CODE

