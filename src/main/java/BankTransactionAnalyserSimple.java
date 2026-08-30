import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyserSimple {
    private static final String RESOURCES = "src/main/resources/";

    public static void main (final String...args) throws IOException{

        final Path path = Paths.get(RESOURCES + args[0]);
        String mes = null;

        if (args.length > 1){
            mes = args[1];
        }

        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (mes == null){
            for(final String line : lines){
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        System.out.println("The total for all transactions is: $" + total);

        } else {

            for(final String line : lines){
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0].replace("\uFEFF", ""), DATE_PATTERN);

            if(date.getMonthValue() == Integer.parseInt(mes)){
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
            }
        
            System.out.println("The total for all transactions in the requested month is: $" + total);

        }

        
    }
}