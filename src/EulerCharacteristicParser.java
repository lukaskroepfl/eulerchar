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
                } else if(field[y][x] == 'O') {
                    if(this.isSurroundedByConnectedRegion(y, x, 'O')) {
                        holes++;
                    }
                }
            }
        }
    }

    private void removeConnectedRegionFromField(int y, int x, char startFieldValue) {
        changeFieldValueTo(y, x, Character.toLowerCase(startFieldValue));

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

    private boolean isSurroundedByConnectedRegion(int y, int x, char startFieldValue) {
        if(canReachAnyEdge(y, x, startFieldValue)) {
            return false;
        }

        changeFieldValueTo(y, x, Character.toLowerCase(startFieldValue));

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
                    isSurroundedByConnectedRegion(y-i, x-j, startFieldValue);
                }
            }
        }

        return true;
    }

    private boolean canReachAnyEdge(int y, int x, char startFieldValue) {
        boolean canReachRightEdge = true;
        boolean canReachLeftEdge = true;
        boolean canReachUpperEdge = true;
        boolean canReachLowerEdge = true;

        for(int j = x; j < field[y].length; j++) {
            if (field[y][j] != startFieldValue) {
                canReachRightEdge = false;
                break;
            }
        }

        for(int j = x; j >= 0; j--) {
            if (field[y][j] != startFieldValue) {
                canReachLeftEdge = false;
                break;
            }
        }

        for(int i = y; i >= 0; i--) {
            if (field[i][x] != startFieldValue) {
                canReachUpperEdge = false;
                break;
            }
        }

        for(int i = y; i < field.length; i++) {
            if (field[i][x] != startFieldValue) {
                canReachLowerEdge = false;
                break;
            }
        }

        return canReachRightEdge || canReachLeftEdge || canReachUpperEdge || canReachLowerEdge;
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

    public int getEulerCaracteristic() {
        return regions - holes;
    }
}