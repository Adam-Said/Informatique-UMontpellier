
public abstract class AbstractFactory {
    
	public abstract Lexer newLexer();
	public abstract Parser newParser();
	public abstract Generator newGenerator();

    public static AbstractFactory compile(String language) throws NotSupportedLanguageException {
        if(language.equals("Java")) {
            return new JavaFactory();
        }
        else if (language.equals("C++")) {
            return new CppFactory();
        }
        else {
            throw new NotSupportedLanguageException("Error: "+ language + " is not supported yet !");
        }
    }

}
