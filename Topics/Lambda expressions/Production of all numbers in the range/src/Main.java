import java.util.function.*;


class Operator {

    public static LongBinaryOperator binaryOperator = (x, y) -> {
        long count = 1;
        if (x == y) {
            return x;
        } else {
            for (long i = x; i <= y; i++) {
                count *= i;
            }
        }
        return count;
    };
}
