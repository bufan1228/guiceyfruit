/**
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.inject.commands;

import com.google.inject.TypeLiteral;
import static com.google.inject.internal.Objects.nonNull;
import com.google.inject.matcher.Matcher;
import com.google.inject.spi.TypeConverter;


/**
 * Immutable snapshot of a request to convert binder types.
 *
 * @deprecated this API is not finalized and should not be depended upon
 * @author jessewilson@google.com (Jesse Wilson)
 */
public final class ConvertToTypesCommand implements Command {
  private final Object source;
  private final Matcher<? super TypeLiteral<?>> typeMatcher;
  private final TypeConverter typeConverter;

  ConvertToTypesCommand(Object source, Matcher<? super TypeLiteral<?>> typeMatcher,
      TypeConverter typeConverter) {
    this.source = nonNull(source, "source");
    this.typeMatcher = nonNull(typeMatcher, "typeMatcher");
    this.typeConverter = nonNull(typeConverter, "typeConverter");
  }

  public Object getSource() {
    return source;
  }

  public Matcher<? super TypeLiteral<?>> getTypeMatcher() {
    return typeMatcher;
  }

  public TypeConverter getTypeConverter() {
    return typeConverter;
  }

  public <T> T acceptVisitor(Visitor<T> visitor) {
    return visitor.visitConvertToTypes(this);
  }
}