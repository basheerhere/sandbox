package sandbox;

/**
 * Count minimal number of jumps from position X to Y.
 * 
 * Scored 100%: https://app.codility.com/demo/results/trainingPEMYCE-PQZ/
 * 
 * @author bash
 *
 */
public class L3FrogJump {

	public static void main(String[] args) {
		int X = 10, Y = 85, D = 30;
		System.out.printf("X = %s, Y = %s, D = %s\n", X, Y, D);
		System.out.println("Minimum jumps required = " + solution(X, Y, D));
	}

	public static int solution(int X, int Y, int D) {
		// Make any one operand a double to perform floating point operation.
		return (int) Math.ceil(((double) Y - X) / D);
	}
}
