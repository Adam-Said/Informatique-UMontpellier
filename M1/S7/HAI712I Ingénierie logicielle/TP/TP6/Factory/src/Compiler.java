
public class Compiler {
    protected Lexer lexer; //analyse lexicale
    protected Parser parser; //analyse syntaxique
    protected Generator gen; //génération de code
    protected AbstractFactory factory;

    public Compiler(String language) throws NotSupportedLanguageException {
        this.factory = AbstractFactory.compile(language);
        this.lexer = this.factory.newLexer();
        this.parser = this.factory.newParser();
        this.gen = this.factory.newGenerator();
    }

    public void compile() {
        System.out.println("====== Compilation starting... ======");
        this.lexer.scan();
        this.parser.parse();
        this.gen.generate();
        System.out.println("======    Compilation done    ======");
    }

}
