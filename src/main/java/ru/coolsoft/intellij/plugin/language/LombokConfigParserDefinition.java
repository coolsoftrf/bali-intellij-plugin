package ru.coolsoft.intellij.plugin.language;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;

import org.jetbrains.annotations.NotNull;

import ru.coolsoft.intellij.plugin.language.parser.LombokConfigParser;
import ru.coolsoft.intellij.plugin.language.psi.LombokConfigFile;
import ru.coolsoft.intellij.plugin.language.psi.LombokConfigTypes;

public class LombokConfigParserDefinition implements ParserDefinition {
  private static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
  private static final TokenSet COMMENTS = TokenSet.create(LombokConfigTypes.COMMENT);

  private static final IFileElementType FILE = new IFileElementType(Language.findInstance(LombokConfigLanguage.class));

  @NotNull
  @Override
  public Lexer createLexer(Project project) {
    return new LombokConfigLexerAdapter();
  }

  @Override
  @NotNull
  public TokenSet getWhitespaceTokens() {
    return WHITE_SPACES;
  }

  @Override
  @NotNull
  public TokenSet getCommentTokens() {
    return COMMENTS;
  }

  @Override
  @NotNull
  public TokenSet getStringLiteralElements() {
    return TokenSet.EMPTY;
  }

  @Override
  @NotNull
  public PsiParser createParser(final Project project) {
    return new LombokConfigParser();
  }

  @Override
  public @NotNull IFileElementType getFileNodeType() {
    return FILE;
  }

  @Override
  public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
    return new LombokConfigFile(viewProvider);
  }

  @Override
  @NotNull
  public PsiElement createElement(ASTNode node) {
    return LombokConfigTypes.Factory.createElement(node);
  }
}
