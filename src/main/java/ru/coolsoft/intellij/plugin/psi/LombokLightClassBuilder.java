package ru.coolsoft.intellij.plugin.psi;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.light.LightPsiClassBuilder;
import com.intellij.psi.impl.source.PsiExtensibleClass;

import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import javax.swing.Icon;

import ru.coolsoft.intellij.plugin.icon.LombokIcons;

public class LombokLightClassBuilder extends LightPsiClassBuilder implements PsiExtensibleClass, SyntheticElement {

  private final String myQualifiedName;
  private final Icon myBaseIcon;
  private final LombokLightModifierList myModifierList;

  private boolean myIsEnum;
  private PsiField[] myFields;
  private PsiMethod[] myMethods;

  private Supplier<? extends Collection<PsiField>> fieldSupplier = Collections::emptyList;
  private Supplier<? extends Collection<PsiMethod>> methodSupplier = Collections::emptyList;

  public LombokLightClassBuilder(@NotNull PsiElement context, @NotNull String simpleName, @NotNull String qualifiedName) {
    super(context, simpleName);
    myIsEnum = false;
    myQualifiedName = qualifiedName;
    myBaseIcon = LombokIcons.CLASS_ICON;
    myModifierList = new LombokLightModifierList(context.getManager(), context.getLanguage());
  }

  @NotNull
  @Override
  public LombokLightModifierList getModifierList() {
    return myModifierList;
  }

  @Override
  public PsiElement getScope() {
    if (getContainingClass() != null) {
      return getContainingClass().getScope();
    }
    return super.getScope();
  }

  @Override
  public PsiElement getParent() {
    return getContainingClass();
  }

  @Nullable
  @Override
  public String getQualifiedName() {
    return myQualifiedName;
  }

  @Override
  public Icon getElementIcon(final int flags) {
    return myBaseIcon;
  }

  @Override
  public TextRange getTextRange() {
    TextRange r = super.getTextRange();
    return r == null ? TextRange.EMPTY_RANGE : r;
  }

  @Override
  public PsiFile getContainingFile() {
    if (null != getContainingClass()) {
      return getContainingClass().getContainingFile();
    }
    return super.getContainingFile();
  }

  @Override
  public boolean isEnum() {
    return myIsEnum;
  }

  @Override
  public PsiField @NotNull [] getFields() {
    if (null == myFields) {
      Collection<PsiField> generatedFields = fieldSupplier.get();
      myFields = generatedFields.toArray(PsiField.EMPTY_ARRAY);
      fieldSupplier = Collections::emptyList;
    }
    return myFields;
  }

  @Override
  public PsiMethod @NotNull [] getMethods() {
    if (null == myMethods) {
      Collection<PsiMethod> generatedMethods = methodSupplier.get();
      myMethods = generatedMethods.toArray(PsiMethod.EMPTY_ARRAY);
      methodSupplier = Collections::emptyList;
    }
    return myMethods;
  }

  @Override
  public @NotNull List<PsiField> getOwnFields() {
    return Collections.emptyList();
  }

  @Override
  public @NotNull List<PsiMethod> getOwnMethods() {
    return Collections.emptyList();
  }

  @Override
  public @NotNull List<PsiClass> getOwnInnerClasses() {
    return Collections.emptyList();
  }

  public LombokLightClassBuilder withFieldSupplier(final Supplier<? extends Collection<PsiField>> fieldSupplier) {
    this.fieldSupplier = fieldSupplier;
    return this;
  }

  public LombokLightClassBuilder withMethodSupplier(final Supplier<? extends Collection<PsiMethod>> methodSupplier) {
    this.methodSupplier = methodSupplier;
    return this;
  }

  public LombokLightClassBuilder withEnum(boolean isEnum) {
    myIsEnum = isEnum;
    return this;
  }

  public LombokLightClassBuilder withImplicitModifier(@PsiModifier.ModifierConstant @NotNull @NonNls String modifier) {
    myModifierList.addImplicitModifierProperty(modifier);
    return this;
  }

  public LombokLightClassBuilder withModifier(@PsiModifier.ModifierConstant @NotNull @NonNls String modifier) {
    myModifierList.addModifier(modifier);
    return this;
  }

  public LombokLightClassBuilder withContainingClass(@NotNull PsiClass containingClass) {
    setContainingClass(containingClass);
    return this;
  }

  public LombokLightClassBuilder withNavigationElement(PsiElement navigationElement) {
    setNavigationElement(navigationElement);
    return this;
  }

  public LombokLightClassBuilder withExtends(PsiClassType baseClassType) {
    getExtendsList().addReference(baseClassType);
    return this;
  }

  public LombokLightClassBuilder withParameterTypes(@Nullable PsiTypeParameterList parameterList) {
    if (parameterList != null) {
      Stream.of(parameterList.getTypeParameters()).forEach(this::withParameterType);
    }
    return this;
  }

  public LombokLightClassBuilder withParameterType(@NotNull PsiTypeParameter psiTypeParameter) {
    getTypeParameterList().addParameter(psiTypeParameter);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    LombokLightClassBuilder that = (LombokLightClassBuilder)o;

    return myQualifiedName.equals(that.myQualifiedName);
  }

  @Override
  public int hashCode() {
    return myQualifiedName.hashCode();
  }
}
