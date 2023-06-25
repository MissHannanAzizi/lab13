/*
 * METHOD FOR TRANSLATE LANGAUGE -> Malaysia/ Arabic/Korea
 * @author nurulhannan
 * */

public class TranslateText {
    public static String translate(String text, String targetLanguage) {
        switch (text) {
            case "Good morning":
                switch (targetLanguage) {
                    case "Bahasa Malaysia":
                        return "Selamat pagi";
                    case "Arabic":
                        return "الخير صباح";
                    case "Korean":
                        return "좋은 아침";
                }
                break;
            case "Good night":
                switch (targetLanguage) {
                    case "Bahasa Malaysia":
                        return "Selamat malam";
                    case "Arabic":
                        return "مساؤك طاب";
                    case "Korean":
                        return "안녕히 주무세요";
                }
                break;
            case "How are you?":
                switch (targetLanguage) {
                    case "Bahasa Malaysia":
                        return "Apa khabar?";
                    case "Arabic":
                        return "حالك؟";
                    case "Korean":
                        return "어떻게 지내세요?";
                }
                break;
            case "Thank you":
                switch (targetLanguage) {
                    case "Bahasa Malaysia":
                        return "Terima kasih";
                    case "Arabic":
                        return "لك شكرا";
                    case "Korean":
                        return "감사합니다";
                }
                break;
            case "Goodbye":
                switch (targetLanguage) {
                    case "Bahasa Malaysia":
                        return "Selamat tinggal";
                    case "Arabic":
                        return "السالمة";
                    case "Korean":
                        return "안녕";
                }
                break;
            case "What’s up?":
                switch (targetLanguage) {
                    case "Bahasa Malaysia":
                        return "Ada apa?";
                    case "Arabic":
                        return "أخبارك؟";
                    case "Korean":
                        return "뭐야?";
                }
                break;
        }
        return "Translation not found";
    }
}
