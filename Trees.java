private static class Trees
    {
        private String name;
        private String type;

        private Trees(String name, String type) {
            this.name = name;
            this.type = type;
        }

        private void printAll() {
            System.out.println("Name: " + name + "\nType: " + type);
        }
    }
