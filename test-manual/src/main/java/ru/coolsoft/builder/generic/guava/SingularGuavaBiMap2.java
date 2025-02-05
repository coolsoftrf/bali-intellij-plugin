// Generated by delombok at Fri Jun 10 19:32:30 CEST 2016
package ru.coolsoft.builder.generic.guava;

import com.google.common.collect.ImmutableBiMap;

public class SingularGuavaBiMap2<A, B> {
  private ImmutableBiMap rawTypes;
  private ImmutableBiMap<Integer, Float> integers;
  private ImmutableBiMap<A, B> generics;
  private ImmutableBiMap<? extends Number, ? extends String> extendsGenerics;

  public static void main(String[] args) {
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  SingularGuavaBiMap2(final ImmutableBiMap rawTypes, final ImmutableBiMap<Integer, Float> integers, final ImmutableBiMap<A, B> generics, final ImmutableBiMap<? extends Number, ? extends String> extendsGenerics) {
    this.rawTypes = rawTypes;
    this.integers = integers;
    this.generics = generics;
    this.extendsGenerics = extendsGenerics;
  }


  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public static class SingularGuavaBiMapBuilder<A, B> {
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableBiMap.Builder<java.lang.Object, java.lang.Object> rawTypes;
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableBiMap.Builder<Integer, Float> integers;
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableBiMap.Builder<A, B> generics;
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    private com.google.common.collect.ImmutableBiMap.Builder<Number, String> extendsGenerics;

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    SingularGuavaBiMapBuilder() {
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> rawType(final java.lang.Object key, final java.lang.Object value) {
      if (this.rawTypes == null) this.rawTypes = com.google.common.collect.ImmutableBiMap.builder();
      this.rawTypes.put(key, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> rawTypes(final java.util.Map<?, ?> rawTypes) {
      if (this.rawTypes == null) this.rawTypes = com.google.common.collect.ImmutableBiMap.builder();
      this.rawTypes.putAll(rawTypes);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> clearRawTypes() {
      this.rawTypes = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> integer(final Integer key, final Float value) {
      if (this.integers == null) this.integers = com.google.common.collect.ImmutableBiMap.builder();
      this.integers.put(key, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> integers(final java.util.Map<? extends Integer, ? extends Float> integers) {
      if (this.integers == null) this.integers = com.google.common.collect.ImmutableBiMap.builder();
      this.integers.putAll(integers);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> clearIntegers() {
      this.integers = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> generic(final A key, final B value) {
      if (this.generics == null) this.generics = com.google.common.collect.ImmutableBiMap.builder();
      this.generics.put(key, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> generics(final java.util.Map<? extends A, ? extends B> generics) {
      if (this.generics == null) this.generics = com.google.common.collect.ImmutableBiMap.builder();
      this.generics.putAll(generics);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> clearGenerics() {
      this.generics = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> extendsGeneric(final Number key, final String value) {
      if (this.extendsGenerics == null) this.extendsGenerics = com.google.common.collect.ImmutableBiMap.builder();
      this.extendsGenerics.put(key, value);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> extendsGenerics(final java.util.Map<? extends Number, ? extends String> extendsGenerics) {
      if (this.extendsGenerics == null) this.extendsGenerics = com.google.common.collect.ImmutableBiMap.builder();
      this.extendsGenerics.putAll(extendsGenerics);
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMapBuilder<A, B> clearExtendsGenerics() {
      this.extendsGenerics = null;
      return this;
    }

    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public SingularGuavaBiMap2<A, B> build() {
      com.google.common.collect.ImmutableBiMap<java.lang.Object, java.lang.Object> rawTypes = this.rawTypes == null ? com.google.common.collect.ImmutableBiMap.<java.lang.Object, java.lang.Object>of() : this.rawTypes.build();
      com.google.common.collect.ImmutableBiMap<Integer, Float> integers = this.integers == null ? com.google.common.collect.ImmutableBiMap.<Integer, Float>of() : this.integers.build();
      com.google.common.collect.ImmutableBiMap<A, B> generics = this.generics == null ? com.google.common.collect.ImmutableBiMap.<A, B>of() : this.generics.build();
      com.google.common.collect.ImmutableBiMap<Number, String> extendsGenerics = this.extendsGenerics == null ? com.google.common.collect.ImmutableBiMap.<Number, String>of() : this.extendsGenerics.build();
      return new SingularGuavaBiMap2<A, B>(rawTypes, integers, generics, extendsGenerics);
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public java.lang.String toString() {
      return "SingularGuavaBiMap2.SingularGuavaBiMapBuilder(rawTypes=" + this.rawTypes + ", integers=" + this.integers + ", generics=" + this.generics + ", extendsGenerics=" + this.extendsGenerics + ")";
    }
  }

  @java.lang.SuppressWarnings("all")
  @javax.annotation.Generated("lombok")
  public static <A, B> SingularGuavaBiMapBuilder<A, B> builder() {
    return new SingularGuavaBiMapBuilder<A, B>();
  }
}
