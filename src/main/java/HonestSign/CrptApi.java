package HonestSign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

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

    public JSONObject convertToJson(Document document, String signature){

        JSONObject jsonObject = new JSONObject();
        if(document.getDescription() != null){
            JSONObject descriptionObject = new JSONObject();
            descriptionObject.put("participantInn", document.getParticipant_inn());
            jsonObject.put("description", descriptionObject);
        }
        jsonObject.put("doc_id", document.getDoc_id());
        jsonObject.put("doc_status", document.getDoc_status());
        jsonObject.put("doc_type", document.getDoc_type());
        jsonObject.put("importRequest", document.getImportRequest());
        jsonObject.put("owner_inn", document.getOwner_inn());
        jsonObject.put("participant_inn", document.getParticipant_inn());
        jsonObject.put("producer_inn", document.getProducer_inn());
        jsonObject.put("production_date", document.getProduction_date());
        jsonObject.put("production_type", document.getProduction_type());

        if(document.getProducts() != null){
            for(Product element : document.products){
                JSONObject productObject = new JSONObject();
                /* Здесь логика построена отдельными друг от друга if, без else и else if, поскольку в описании API нет
                указания на то, что эти 3 необязательные параметры не могут быть заполнены по отдельности (например,
                допускаю, что может фигурировать номер сертификата, но не стоять дата и номер). */
                if(element.getCertificate_document() != null || !element.getCertificate_document().isEmpty()){
                    productObject.put("certificate_document", element.getCertificate_document());
                }
                if(element.getCertificate_document_date() != null || !element.getCertificate_document_date().isEmpty()){
                    productObject.put("certificate_document_date", element.getCertificate_document_date());
                }
                if(element.getCertificate_document_number() != null || !element.getCertificate_document_number().isEmpty()){
                    productObject.put("certificate_document_number", element.getCertificate_document_number());
                }
                productObject.put("owner_inn", element.getOwner_inn());
                productObject.put("producer_inn", element.getProducer_inn());
                if(!element.getProduction_date().equals(document.getProduction_date())){
                    productObject.put("production_date", element.getProduction_date());
                }
                productObject.put("tnved_code", element.getTnved_code());
                if(element.getUitu_code() != null || !element.getUitu_code().isEmpty()){
                    productObject.put("uit_code", element.getUit_code());
                }
                if(element.getUit_code() != null || !element.getUit_code().isEmpty()){
                    productObject.put("uitu_code", element.getUitu_code());
                }
                jsonObject.put("products", productObject);
            }
        }
        jsonObject.put("reg_date", document.getReg_date());
        jsonObject.put("reg_number", document.getReg_number());
        return jsonObject;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Document{

        private Description description;

        private String doc_id;
        private String doc_status;
        private String doc_type;
        private String importRequest;
        private String owner_inn;
        private String participant_inn;
        private String producer_inn;
        private String production_date;
        private String production_type;

        private Product[] products;

        private String reg_date;
        /*Автоматически присваивается при регистрации документа (переводе в статус «Создан»).
        Формат: YYYY-MM-DDTHH:mm:ss (московское время)*/
        private String reg_number;
    }

    @Getter
    @Setter
    @AllArgsConstructor
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
    }

    public static class Description{
        private String participantInn;

        public Description(String participantInn) {
            this.participantInn = participantInn;
        }
    }
}
