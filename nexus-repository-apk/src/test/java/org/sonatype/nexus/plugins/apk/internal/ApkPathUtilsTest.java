/*
 * Sonatype Nexus (TM) Open Source Version
 * Copyright (c) 2019-present Sonatype, Inc.
 * All rights reserved. Includes the third-party code listed at http://links.sonatype.com/products/nexus/oss/attributions.
 *
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License Version 1.0,
 * which accompanies this distribution and is available at http://www.eclipse.org/legal/epl-v10.html.
 *
 * Sonatype Nexus (TM) Professional Version is available from Sonatype, Inc. "Sonatype" and "Sonatype Nexus" are trademarks
 * of Sonatype, Inc. Apache Maven is a trademark of the Apache Software Foundation. M2eclipse is a trademark of the
 * Eclipse Foundation. All other trademarks are the property of their respective owners.
 */
package org.sonatype.nexus.plugins.apk.internal;

import java.util.Map;

import org.sonatype.goodies.testsupport.TestSupport;
import org.sonatype.nexus.repository.view.matchers.token.TokenMatcher;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class ApkPathUtilsTest
    extends TestSupport
{
  private ApkPathUtils underTest;

  @Mock
  private TokenMatcher.State state;

  @Mock
  Map<String, String> tokens;

  @Before
  public void setUp() throws Exception {
    underTest = new ApkPathUtils();
  }

  @Test
  public void versionParseTest() throws Exception {
    when(state.getTokens()).thenReturn(tokens);
    when(tokens.get("filename")).thenReturn("a2ps-doc-4.14-r5");

    String result = underTest.version(state);

    assertThat(result, is(equalTo("4.14-r5")));

    when(tokens.get("filename")).thenReturn("a2ps-doc-4.14.32-r51");

    result = underTest.version(state);

    assertThat(result, is(equalTo("4.14.32-r51")));
  }

  @Test
  public void nameParseTest() throws Exception {
    when(state.getTokens()).thenReturn(tokens);
    when(tokens.get("filename")).thenReturn("a2ps-doc-4.14-r5");

    String result = underTest.name(state);

    assertThat(result, is(equalTo("a2ps-doc")));

    when(tokens.get("filename")).thenReturn("a2ps-doc-4.14.32-r51");

    result = underTest.name(state);

    assertThat(result, is(equalTo("a2ps-doc")));
  }
}