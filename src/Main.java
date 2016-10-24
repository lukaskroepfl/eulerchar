import java.util.Date;

public class Main {

    public static void main(String[] args) {
        char[][] field = getExampleField();

        EulerCharacteristicParser eulerCharacteristicParser = new EulerCharacteristicParser(field);

        Date start = new Date();

        eulerCharacteristicParser.parseField();

        Date end = new Date();

        Long parseTime = end.getTime() - start.getTime();

        int connectedRegions = eulerCharacteristicParser.getConnectedRegionsCount();
        int holes = eulerCharacteristicParser.getHolesCount();
        int eulerCharacteristic = eulerCharacteristicParser.getEulerCaracteristic();

        System.out.println(String.format("Parse time in ms: %d", parseTime));

        System.out.println(String.format("Connected regions: %d", connectedRegions));
        System.out.println(String.format("Holes: %d", holes));
        System.out.println(String.format("Euler characteristic: %d", eulerCharacteristic));
    }

    private static char[][] getExampleField() {
        return new char[][]{
                {'O', 'O', 'O', 'O', 'O'},
                {'O', 'X', 'X', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'O', 'X', 'O'},
                {'O', 'X', 'X', 'X', 'O'},
                {'O', 'O', 'O', 'O', 'O'},
        };
    }
}
