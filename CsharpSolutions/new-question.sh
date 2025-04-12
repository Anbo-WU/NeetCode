#!/bin/bash

# Access Question Name
QUESTION_NAME=$1

if [ -z "$QUESTION_NAME" ]; then
  echo "❌ Please provide a question name. Usage: ./new-question.sh QuestionName"
  exit 1
fi

# The Path of Main Project and Test
MAIN_DIR="InterviewCoding"
TEST_DIR="InterviewCodingTest"

# File Path
MAIN_FILE="$MAIN_DIR/${QUESTION_NAME}.cs"
TEST_FILE="$TEST_DIR/${QUESTION_NAME}Test.cs"

# Create Main Project Coding File
cat <<EOF > "$MAIN_FILE"
namespace InterviewCoding
{
    public class ${QUESTION_NAME}
    {
        public static int Solution()
        {
            // TODO: Implement here
            return 0;
        }
    }
}
EOF

# Create Test File
cat <<EOF > "$TEST_FILE"
using Xunit;
using InterviewCoding;

namespace InterviewCodingTest
{
    public class ${QUESTION_NAME}Test
    {
        [Fact]
        public void Test_SampleInput_ReturnsExpected()
        {
            var result = ${QUESTION_NAME}.Solution();
            Assert.Equal(0, result);
        }
    }
}
EOF

echo "✅ Files created:"
echo " - $MAIN_FILE"
echo " - $TEST_FILE"
