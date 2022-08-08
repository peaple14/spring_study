package hello.typeconverter.formatter;

import org.springframework.format.Formatter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class MyNumberFormatter implements Formatter<Number> {

    @Override
    public Number parse(String text, Locale locale) throws ParseException {
        /*1,000 -> 1000*/
        NumberFormat format = NumberFormat.getInstance();
        return format.parse(text);

    }

    @Override
    public String print(Number object, Locale locale) {
        NumberFormat format = NumberFormat.getInstance(locale);
        return NumberFormat.getInstance(locale).format(object);
    }
}
