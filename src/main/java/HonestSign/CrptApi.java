package HonestSign;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

public class CrptApi {

    /*
    fixme Методы API преимущественно возвращают ответы в формате JSON, т.е. MIME-type
     application/json. Некоторые методы в корректном ответе возвращают двоичное содержимое
     (например, документы в zip) MIME-type application/zip или application/octet-stream, а в случае
     ошибки - описание этой самой ошибки в JSON. Таким образом, один и тот же метод в
     зависимости от ситуации может вернуть разные типы содержимого (контента).
    */

    private final TimeUnit timeUnit;

    private final int requestLimit;

    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
    }

    @Getter
    @Setter
    public static class Document{

    }
}
