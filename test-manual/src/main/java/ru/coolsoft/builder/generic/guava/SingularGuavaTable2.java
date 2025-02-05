// Generated by delombok at Fri Jun 10 19:32:41 CEST 2016
package ru.coolsoft.builder.generic.guava;

import com.google.common.collect.ImmutableTable;

public class SingularGuavaTable2<A, B, C> {
  private ImmutableTable rawTypes;
  private ImmutableTable<Integer, Float, String> integers;
  private ImmutableTable<A, B, C> generics;
  private ImmutableTable<? extends Number, ? extends Float, ? extends String> extendsGenerics;

  public static void main(String[] args) {
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  SingularGuavaTable2(final ImmutableTable rawTypes, final ImmutableTable<Integer, Float, String> integers, final ImmutableTable<A, B, C> generics, final ImmutableTable<? extends Number, ? extends Float, ? extends String> extendsGenerics) {
    this.rawTypes = rawTypes;
    this.integers = integers;
    this.generics = generics;
    this.extendsGenerics = extendsGenerics;
  }


  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public static class SingularGuavaTableBuilder<A, B, C> {
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableTable.Builder<java.lang.Object, java.lang.Object, java.lang.Object> rawTypes;
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableTable.Builder<Integer, Float, String> integers;
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableTable.Builder<A, B, C> generics;
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableTable.Builder<Number, Float, String> extendsGenerics;

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    SingularGuavaTableBuilder() {
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> rawType(final java.lang.Object rowKey, final java.lang.Object columnKey, final java.lang.Object value) {
      if (this.rawTypes == null) this.rawTypes = com.google.common.collect.ImmutableTable.builder();
      this.rawTypes.put(rowKey, columnKey, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> rawTypes(final com.google.common.collect.Table<?, ?, ?> rawTypes) {
      if (this.rawTypes == null) this.rawTypes = com.google.common.collect.ImmutableTable.builder();
      this.rawTypes.putAll(rawTypes);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> clearRawTypes() {
      this.rawTypes = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> integer(final Integer rowKey, final Float columnKey, final String value) {
      if (this.integers == null) this.integers = com.google.common.collect.ImmutableTable.builder();
      this.integers.put(rowKey, columnKey, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> integers(final com.google.common.collect.Table<? extends Integer, ? extends Float, ? extends String> integers) {
      if (this.integers == null) this.integers = com.google.common.collect.ImmutableTable.builder();
      this.integers.putAll(integers);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> clearIntegers() {
      this.integers = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> generic(final A rowKey, final B columnKey, final C value) {
      if (this.generics == null) this.generics = com.google.common.collect.ImmutableTable.builder();
      this.generics.put(rowKey, columnKey, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> generics(final com.google.common.collect.Table<? extends A, ? extends B, ? extends C> generics) {
      if (this.generics == null) this.generics = com.google.common.collect.ImmutableTable.builder();
      this.generics.putAll(generics);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> clearGenerics() {
      this.generics = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> extendsGeneric(final Number rowKey, final Float columnKey, final String value) {
      if (this.extendsGenerics == null) this.extendsGenerics = com.google.common.collect.ImmutableTable.builder();
      this.extendsGenerics.put(rowKey, columnKey, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> extendsGenerics(final com.google.common.collect.Table<? extends Number, ? extends Float, ? extends String> extendsGenerics) {
      if (this.extendsGenerics == null) this.extendsGenerics = com.google.common.collect.ImmutableTable.builder();
      this.extendsGenerics.putAll(extendsGenerics);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTableBuilder<A, B, C> clearExtendsGenerics() {
      this.extendsGenerics = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaTable2<A, B, C> build() {
      com.google.common.collect.ImmutableTable<java.lang.Object, java.lang.Object, java.lang.Object> rawTypes = this.rawTypes == null ? com.google.common.collect.ImmutableTable.<java.lang.Object, java.lang.Object, java.lang.Object>of() : this.rawTypes.build();
      com.google.common.collect.ImmutableTable<Integer, Float, String> integers = this.integers == null ? com.google.common.collect.ImmutableTable.<Integer, Float, String>of() : this.integers.build();
      com.google.common.collect.ImmutableTable<A, B, C> generics = this.generics == null ? com.google.common.collect.ImmutableTable.<A, B, C>of() : this.generics.build();
      com.google.common.collect.ImmutableTable<Number, Float, String> extendsGenerics = this.extendsGenerics == null ? com.google.common.collect.ImmutableTable.<Number, Float, String>of() : this.extendsGenerics.build();
      return new SingularGuavaTable2<A, B, C>(rawTypes, integers, generics, extendsGenerics);
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.lang.String toString() {
      return "SingularGuavaTable2.SingularGuavaTableBuilder(rawTypes=" + this.rawTypes + ", integers=" + this.integers + ", generics=" + this.generics + ", extendsGenerics=" + this.extendsGenerics + ")";
    }
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public static <A, B, C> SingularGuavaTableBuilder<A, B, C> builder() {
    return new SingularGuavaTableBuilder<A, B, C>();
  }
}
