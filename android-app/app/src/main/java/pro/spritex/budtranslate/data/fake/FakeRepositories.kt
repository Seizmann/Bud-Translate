package pro.spritex.budtranslate.data.fake

data class ChatMessage(
    val id: String,
    val originalText: String,
    val translatedText: String,
    val isSelf: Boolean,
    val timestamp: String
)

data class Contact(
    val id: String,
    val name: String,
    val email: String,
    val defaultLanguage: String,
    val isOnline: Boolean
)

data class TranslationResult(
    val sourceText: String,
    val sourceLang: String,
    val targetText: String,
    val targetLang: String
)

object FakeRepositories {
    val languages = listOf("English", "Bengali", "Spanish", "French", "German", "Polish", "Japanese", "Arabic")

    val contacts = listOf(
        Contact("1", "Mohammad Sijan", "sijan@spritexai.pro.bd", "Bengali", true),
        Contact("2", "Rexio AI", "hello@rexio.pro", "English", true),
        Contact("3", "Spritex Operator", "dev@spritexai.pro.bd", "French", false)
    )

    private val staticTranslations = mapOf(
        "hello" to mapOf("Bengali" to "হ্যালো", "Spanish" to "hola", "French" to "bonjour"),
        "how are you?" to mapOf("Bengali" to "কেমন আছেন?", "Spanish" to "¿cómo estás?", "French" to "comment ça va?"),
        "i love building apps" to mapOf("Bengali" to "আমি অ্যাপস তৈরি করতে ভালোবাসি", "Spanish" to "me encanta crear aplicaciones", "French" to "j'adore créer des applications"),
        "real-time call translation is fast" to mapOf("Bengali" to "রিয়েল-টাইম কল অনুবাদ দ্রুত", "Spanish" to "la traducción de llamadas en tiempo real es rápida", "French" to "la traduction d'appels en temps réel est rapide"),
        "this is earbud mode, listening..." to mapOf("Bengali" to "এটি ইয়ারবাড মোড, শুনছি...", "Spanish" to "este es el modo de auricular, escuchando...", "French" to "c'est le mode oreillette, écoute en cours...")
    )

    fun translate(text: String, fromLang: String, toLang: String): String {
        val clean = text.trim().lowercase()
        val languageTranslations = staticTranslations[clean] ?: return "Translated: $text"
        return languageTranslations[toLang] ?: "[$toLang] $text"
    }

    val demoChatHistory = listOf(
        ChatMessage("1", "Hello! How can I help you today?", "হ্যালো! আজ আমি আপনাকে কীভাবে সাহায্য করতে পারি?", false, "10:00 AM"),
        ChatMessage("2", "I want to translate this conversation in real-time.", "আমি রিয়েল-টাইমে এই কথোপকথনটি অনুবাদ করতে চাই।", true, "10:01 AM"),
        ChatMessage("3", "No problem, Bud Translate handles it with sub-second latency.", "কোনো সমস্যা নেই, বাড ট্রান্সলেট এটি সাব-সেকেন্ড লেটেন্সিতে সম্পন্ন করে।", false, "10:02 AM"),
        ChatMessage("4", "That sounds perfect. Let's start the Audio Call soon.", "চমৎকার শোনাচ্ছে। চলুন শীঘ্রই অডিও কল শুরু করি।", true, "10:03 AM")
    )
}
