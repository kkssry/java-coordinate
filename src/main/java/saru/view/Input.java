package saru.view;

import saru.domain.*;

import java.util.*;

// SonarLint StandOutput warning 방지용
@java.lang.SuppressWarnings("squid:S106")
public class Input {
    private static final String INPUT_USER_INPUT = "좌표를 입력하세요.";
    private static final String INPUT_RANGE_ERROR = "범위가 잘못 되었습니다. 다시 입력하세요";
    private static final String INPUT_SAME_VALUE_ERROR = "같은 포인트를 두번이상 입력했습니다. 다시 입력하세요.";
    private static final String INPUT_NUM_ERROR = "포인트 갯수가 잘못 되었습니다.";
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final String REGEX = "[\\(,\\)]";

    private static Scanner scanner = new Scanner(System.in);

    private Input() {
        // empty
    }

    // 라인수
    public static Set<Point> getUserInputProc() {
        // 유저에게 텍스트 입력 받는다
        Set<Point> points = null;
        boolean isSuccess;

        do {
            String userInputString = getUserInput();

            try {
                points = getSplitedUserInputString(userInputString);
                isSuccess = true;
            } catch (IllegalArgumentException e) {
                isSuccess = false;
            }
        } while (!isSuccess);

        return points;
    }

    public static String getUserInput() {
        System.out.println();
        System.out.println(INPUT_USER_INPUT);
        return scanner.nextLine();
    }

    private static void loopAssignmentProc(String[] lineSplitArr, Set<Point> points) {
        for (int i = 0; i < lineSplitArr.length; i++) {
            String[] pointSplitArr = lineSplitArr[i].split(REGEX);
            assignmentSplitString(points, pointSplitArr);
        }
        checkPoint(lineSplitArr.length, points.size());
    }

    private static void checkPoint(int length, int size) {
        if (length != size) {
            System.out.println(INPUT_SAME_VALUE_ERROR);
            throw new IllegalArgumentException("같은 포인트를 중복 입력");
        }

        if (length <= 1 || length > 4) {
            System.out.println(INPUT_NUM_ERROR);
            throw new IllegalArgumentException("포인트 입력 갯수가 잘못됨");
        }
    }

    private static void assignmentSplitString(Set<Point> points, String[] pointSplitArr) {
        try {
            // 첫 문자가 구분자라서 0번 인덱스는 ""(empty string)
            points.add(new Point(Double.parseDouble(pointSplitArr[FIRST_INDEX]),
                    Double.parseDouble(pointSplitArr[SECOND_INDEX]),
                    true));
        } catch (IllegalArgumentException e) {
            System.out.println(INPUT_RANGE_ERROR);
            throw new IllegalArgumentException(e);
        }
    }

    public static Set<Point> getSplitedUserInputString(String userInputString) {
        String[] lineSplitArr = userInputString.split("-");
        Set<Point> points = new HashSet<>();

        loopAssignmentProc(lineSplitArr, points);

        return points;
    }
}