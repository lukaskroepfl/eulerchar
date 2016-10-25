public class EulerCharacteristicParser {
    private char[][] field;
    private int regions;
    private int holes;

    public EulerCharacteristicParser(char[][] field) {
        this.field = field;
        this.regions = 0;
        this.holes = 0;
    }

    public void parseField() {
        for(int y = 0; y < field.length; y++) {
            for(int x = 0; x < field[y].length; x++) {
                if(field[y][x] == 'X') {
                    regions++;
                    this.removeConnectedRegionFromField(y, x, 'X');
                } else if(field[y][x] == ' ') {
                    if(!this.hasFieldPathToEdge(y, x, ' ', 0, 0)) {
                        this.removeConnectedRegionFromField(y, x, ' ');
                        holes++;
                    }
                }
            }
        }
    }

    private void removeConnectedRegionFromField(int y, int x, char startFieldValue) {
        changeFieldValueTo(y, x, 'x');

        for(int i=-1; i < 2; i++) {
            for(int j=-1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if(y-i < 0 || x-j < 0) {
                    continue;
                }

                if(y-i > field.length-1 || x-j > field[y].length-1) {
                    continue;
                }

                if (field[y-i][x-j] == startFieldValue) {
                    removeConnectedRegionFromField(y-i, x-j, startFieldValue);
                }
            }
        }
    }

    private boolean hasFieldPathToEdge(int y, int x, char startFieldValue, int iCalled, int jCalled) {
        if(isEdge(y, x)) {
            return true;
        }

        for(int i = -1; i < 2; i++) {
            for(int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                if((i*-1) == iCalled && (j*-1) == jCalled) {
                    continue;
                }

                if (field[y+i][x+j] == startFieldValue) {
                    if(hasFieldPathToEdge(y+i, x+j, startFieldValue, i, j)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isEdge(int y, int x) {
        if(y == 0) {
            return true;
        }

        if(x == 0) {
            return true;
        }

        if(y == (field.length - 1)) {
            return true;
        }

        if(x == (field[y].length - 1)) {
            return true;
        }

        return false;
    }

    private void changeFieldValueTo(int y, int x, char value) {
        field[y][x] = value;
    }

    public int getConnectedRegionsCount() {
        return regions;
    }

    public int getHolesCount() {
        return holes;
    }

    public int getEulerCharacteristic() {
        return regions - holes;
    }
}