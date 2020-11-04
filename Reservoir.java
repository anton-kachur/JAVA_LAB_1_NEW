 private static class Reservoir
    {
        private String reservoir_type;
        private double depth;

        private Reservoir(String type, double d) {
            reservoir_type = type;
            depth = d;
        }

        private void printAll() {
            System.out.println("Reservoir type: " + reservoir_type + "\nDepth: " + depth);
        }
    }
