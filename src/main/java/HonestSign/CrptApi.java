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

        private Description description;

        private String doc_id;
        private String doc_status;
        private String doc_type;
        private String importRequest;
        private String owner_inn;
        private String producer_inn;
        private String production_date;
        private String production_type;

        private Product products;

        private String reg_date;
        /*Автоматически присваивается при регистрации документа (переводе встатус «Создан»).
        Формат: YYYY-MM-DDTHH:mm:ss (московское время)*/
        private String reg_number;

        public Document(Description description,
                        String doc_Id,
                        String doc_Status,
                        String doc_Type,
                        String importRequest,
                        String owner_inn,
                        String producer_inn,
                        String production_date,
                        String production_type,
                        Product products,
                        String reg_date,
                        String reg_number)
        {
            this.description = description;
            this.doc_id = doc_Id;
            this.doc_status = doc_Status;
            this.doc_type = doc_Type;
            this.importRequest = importRequest;
            this.owner_inn = owner_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.production_type = production_type;
            this.products = products;
            this.reg_date = reg_date;
            this.reg_number = reg_number;
        }
    }

    public static class Product{

        private String certificate_document;
        private String certificate_document_date;
        private String certificate_document_number;
        private String owner_inn;
        private String producer_inn;
        private String production_date;
        private String tnved_code;
        private String uit_code;
        private String uitu_code;

        public Product(String certificate_document,
                       String certificate_document_date,
                       String certificate_document_number,
                       String owner_inn, String producer_inn,
                       String production_date, String tnved_code,
                       String uit_code, String uitu_code)
        {
            this.certificate_document = certificate_document;
            this.certificate_document_date = certificate_document_date;
            this.certificate_document_number = certificate_document_number;
            this.owner_inn = owner_inn;
            this.producer_inn = producer_inn;
            this.production_date = production_date;
            this.tnved_code = tnved_code;
            this.uit_code = uit_code;
            this.uitu_code = uitu_code;
        }
    }

    public class Description{
        private String participantInn;

        public Description(String participantInn) {
            this.participantInn = participantInn;
        }
    }
}
