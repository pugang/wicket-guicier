package com.jolira.wicket.guicier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.util.convert.converter.IntegerConverter;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

/**
 * Test something
 */
public class GuicierPageFactoryTest {
    /**
     * Test something
     */
    public static class EnumerationTestPage extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        @Inject
        EnumerationTestPage(@Parameter("state") final State state) {
            assertEquals(State.ON, state);
        }
    }

    /**
     * Interface for testing
     */
    public interface IJolira {
        // nothing
    }

    final class Jolira implements IJolira {
        // nothing
    }

    /**
     * A simple test page with javax
     */
    public static class MyJavaxTest extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        /**
         * @param abcde
         */
        @javax.inject.Inject
        public MyJavaxTest(@Named("abc") final Set<String> abcde) {
            assertEquals(setOf("A", "B", "C"), abcde);
        }
    }

    /**
     * A simple test page
     */
    public static class MyTest extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        /**
         * @param abcde
         */
        @Inject
        public MyTest(@Named("abc") final Set<String> abcde) {
            assertEquals(setOf("A", "B", "C"), abcde);
        }
    }

    static enum State {
        ON, OFF
    }

    private static class StringConverter implements IConverter<String> {
        private static final long serialVersionUID = 5221463436925128138L;

        @Override
        public String convertToObject(final String value, final Locale locale) {
            return "converted:" + value;
        }

        @Override
        public String convertToString(final String value, final Locale locale) {
            return value.toString();
        }
    }

    /**
     * Test something
     */
    public static class TestPage0 extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        /**
         * @param converter
         */
        @Inject
        TestPage0(final IConverter<?> converter) {
            fail();
        }
    }

    /**
     * Test something
     */
    public static class TestPage1 extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        // nothing
    }

    /**
     * Test something
     */
    public static class TestPage12 extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        @Inject
        TestPage12(@Parameter(value = "offset", optional = true) final double offset) {
            assertEquals(0, offset, 0);
        }
    }

    /**
     * Test something
     */
    public static class TestPage13 extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        @Inject
        TestPage13(@Parameter(value = "offset", optional = true) final short offset) {
            assertEquals(0, offset, 0);
        }
    }

    /**
     * Test something
     */
    public static class TestPage14 extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        @Inject
        TestPage14(@Parameter(value = "offset", optional = true) final float offset) {
            assertEquals(0, offset, 0);
        }
    }

    /**
     * Test something
     */
    public static class TestPage15 extends WebPage {
        private static final long serialVersionUID = 4865753504614256826L;

        @Inject
        TestPage15(@Parameter(value = "offset", optional = true) final boolean offset) {
            assertFalse(offset);
        }
    }

    /**
     * Test something
     */
    public static class TestPage16 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPage16(@Parameter(value = "offset", optional = true) final long offset) {
            assertEquals(0, offset);
        }
    }

    /**
     * Test something
     */
    public static class TestPage17 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPage17(@Parameter(value = "offset", optional = true) final char offset) {
            assertEquals(0, offset);
        }
    }

    /**
     * Test something
     */
    public static class TestPage5 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPage5(final IConverter<?> converter, @Parameter("company") final String company) {
            assertEquals("jolira", company);
            assertEquals(IntegerConverter.class, converter.getClass());
        }
    }

    /**
     * Test something
     */
    public static class TestPage5a extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPage5a(final IConverter<?> converter, @Parameter(value = "company", optional = true) final String company) {
            assertNull(company);
            assertEquals(IntegerConverter.class, converter.getClass());
        }
    }

    /**
     * Test something
     */
    public static class TestPage6 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPage6(@Parameter("offset") final int offset) {
            assertEquals(15, offset);
        }
    }

    /**
     * Test something
     */
    public static class TestPage7 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        /**
         * @param value1
         * @param value2
         */
        @Inject
        TestPage7(@Parameter(value = "value1", optional = true) final int value1,
                @Parameter(value = "value2", optional = false) final int value2) {
            fail();
        }

        /**
         * @param offset
         * @param offset2
         * @param offset3
         * @param offset4
         * @param offset5
         * @param offset6
         * @param offset7
         * @param offset8
         * @param offset9
         * @param offset10
         * @param offset11
         * @param success1
         * @param success2
         * @param offset12
         * @param offset13
         * @param offset14
         * @param params
         */
        @Inject
        TestPage7(@Parameter("offset") final int offset, @Parameter("offset") final long offset2,
                @Parameter("offset") final Long offset3, @Parameter("offset") final Integer offset4,
                @Parameter("offset") final short offset5, @Parameter("offset") final Short offset6,
                @Parameter("offset") final float offset7, @Parameter("offset") final Float offset8,
                @Parameter("offset") final Double offset9, @Parameter("offset") final double offset10,
                @Parameter("offset") final BigDecimal offset11, @Parameter("success") final boolean success1,
                @Parameter("success") final Boolean success2,
                @Parameter(value = "offset", converter = StringConverter.class) final String offset12,
                @Parameter("offset") final byte offset13, @Parameter("offset") final Byte offset14,
                final PageParameters params) {
            assertEquals(15, offset2);
            assertEquals(Long.valueOf(15), offset3);
            assertEquals(Integer.valueOf(15), offset4);
            assertEquals(Short.valueOf((short) 15), offset6);
            assertEquals(15, offset5);
            assertEquals(Float.valueOf(15), offset8);
            assertEquals(Float.valueOf(15), Float.valueOf(offset7));
            assertEquals(Double.valueOf(15), offset9);
            assertEquals(Double.valueOf(15), Double.valueOf(offset10));
            assertEquals(BigDecimal.valueOf(15), offset11);
            assertEquals(Boolean.TRUE, success2);
            assertEquals(Boolean.TRUE, Boolean.valueOf(success1));
            assertEquals(Byte.valueOf((byte) 15), offset14);
            assertEquals(Byte.valueOf((byte) 15), Byte.valueOf(offset13));
            assertEquals(2, params.getAllNamed().size());
            assertTrue(params.get("success").toBoolean());
        }
    }

    /**
     * Test something
     */
    public static class TestPage8 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        /**
         * @param converter
         */
        @Inject
        TestPage8(@Named("jfk") final IConverter<?> converter, @Parameter("company") final String company) {
            assertEquals("jolira", company);
        }
    }

    /**
     * Test something
     */
    public static class TestPage9 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        /**
         * @param converter
         * @param company
         */
        @Inject
        TestPage9(@Named("jfk") final IConverter<?> converter, @Parameter("company") final Map<String, String> company) {
            fail();
        }
    }

    /**
     * Test something
     */
    public static class TestPage99 extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        /**
         * @param converter
         * @param state
         */
        @Inject
        TestPage99(final IConverter<?> converter, @Parameter("state") final State state) {
            fail();
        }
    }

    /**
     * Test something
     */
    public static class TestPageFailOnPurpose extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        /**
         * Test something
         */
        public TestPageFailOnPurpose() {
            fail("fail on purpose");
        }
        // nothing
    }

    /**
     * Test something
     */
    public static class TestPageInjectedAndPageParameters extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        /**
         * @param converter
         */
        @Inject
        TestPageInjectedAndPageParameters(final IConverter<?> converter, final PageParameters params) {
            assertEquals(0, params.getNamedKeys().size());
        }
    }

    /**
     * Test something
     */
    public static class TestPageInjectWithParamsOnly extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPageInjectWithParamsOnly(final PageParameters params) {
            assertEquals(1, params.getNamedKeys().size());
        }
    }

    /**
     * Test something
     */
    public static class TestPageIntOffset extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPageIntOffset(@Parameter(value = "offset", optional = true) final int offset) {
            assertEquals(0, offset);
        }
    }

    /**
     * Test something
     */
    public static class TestPageNullParam extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        TestPageNullParam(final PageParameters params) {
            assertNotNull(params);
        }
    }

    /**
     * Test something
     */
    public static class TestPageOptionalByte extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPageOptionalByte(@Parameter(value = "offset", optional = true) final byte offset) {
            assertEquals(0, offset);
        }
    }

    /**
     * Test something
     */
    public static class TestPageOptionalEverything extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestPageOptionalEverything(final IConverter<?> converter,
                @Parameter(value = "offset", optional = true) final byte offset) {
            assertEquals(0, offset);
            assertNotNull(converter);
        }
    }

    /**
     * Test something
     */
    public static class TestPageParamOnly extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        TestPageParamOnly(final PageParameters params) {
            assertEquals(2, params.getNamedKeys().size());
            assertEquals("jolira2", params.get("company2").toString());
            assertEquals("jolira1", params.get("company1").toString());
        }
    }

    /**
     * Test uncleansed parameters
     */
    public static class TestUncleansedParams extends WebPage {
        private static final long serialVersionUID = -3473998300532249033L;

        @Inject
        TestUncleansedParams(final PageParameters uncleansed, @Parameter(value = "offset") final short offset,
                final PageParameters cleansed) {
            assertEquals(3, uncleansed.getNamedKeys().size());
            assertEquals(1, cleansed.getNamedKeys().size());
            assertEquals("0", uncleansed.get("offset").toString());
            assertEquals("a", uncleansed.get("a").toString());
            assertEquals("b", uncleansed.get("b").toString());
            assertEquals("0", cleansed.get("offset").toString());
            assertEquals(0, offset, 0);
        }
    }

    private static final TypeLiteral<IConverter<?>> CONVERTER_TYPE_LITERAL = new TypeLiteral<IConverter<?>>() {
    };

    /**
     * @param <T>
     * @param elements
     *            element to be returned as a set
     * @return return a set
     */
    protected final static <T> Set<T> setOf(final T... elements) {
        final Set<T> result = new HashSet<T>();

        result.addAll(Arrays.asList(elements));

        return result;
    }

    private WicketTester tester;

    /**
     * Test something
     */
    @Before
    public void setUp() {
        tester = new WicketTester();
    }

    /**
     * Test something
     */
    @After
    public void teardown() {
        tester = null;
    }

    /**
     * Test something
     */
    @Test
    public void testEmptyStringInteger() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("offset", "");

        factory.newPage(TestPageIntOffset.class, params);
    }

    /**
     * Test something
     */
    @Test
    public void testEnumerationConverter() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("state", "ON");

        factory.newPage(EnumerationTestPage.class, params);
    }

    /**
     * Test something
     */
    @Test
    public void testEnumerationConverterOrdinals() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("state", "0");

        factory.newPage(EnumerationTestPage.class, params);
    }

    /**
     * Test something
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEnumerationInvalidOrdinal() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("state", "9");

        factory.newPage(EnumerationTestPage.class, params);
    }

    /**
     * Test something
     */
    @Test(expected = WicketRuntimeException.class)
    public void testErrorHandling() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);

        factory.newPage(TestPageFailOnPurpose.class);
    }

    /**
     * Test something
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInjectedAndOneIllegalParameter() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company", "<script/");

        factory.newPage(TestPage5.class, params);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndOneIntParameter() {
        assertNotNull(tester); // make findbugs happy
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("offset", "15");

        final IRequestablePage page = factory.newPage(TestPage6.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndOneIntParameterMultiAnno() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("offset", "15");
        params.add("success", "true");

        final IRequestablePage page = factory.newPage(TestPage7.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndOneIntParameterMultiAnnoLongVal() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("offset", Long.valueOf(15));
        params.add("success", "true");

        final IRequestablePage page = factory.newPage(TestPage7.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndOneIntParameterMultiAnnoLongVals() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("offset", "15");
        params.add("success", "true");

        final IRequestablePage page = factory.newPage(TestPage7.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test(expected = WicketRuntimeException.class)
    public void testInjectedAndOneInvalidTypeParameter() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).annotatedWith(Names.named("jfk")).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company", "jolira");

        factory.newPage(TestPage9.class, params);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndOneOptionalParameter() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final IRequestablePage page = factory.newPage(TestPage5a.class);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndOneParameter() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company", "jolira");

        final IRequestablePage page = factory.newPage(TestPage5.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testInjectedAndPageParameters() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company", "jolira");

        final IRequestablePage page = factory.newPage(TestPageInjectedAndPageParameters.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgument() {
        new GuicierPageFactory(null);
    }

    /**
     * Test the javax {@link Multibinder} injection
     */
    @Test
    public void testJavaxMultibinderInjectSet() {
        final Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                Multibinder<String> multibinder = Multibinder.newSetBinder(binder(), String.class, Names.named("abc"));
                multibinder.addBinding().toInstance("A");
                multibinder.addBinding().toInstance("B");

                multibinder = Multibinder.newSetBinder(binder(), String.class, Names.named("abc"));
                multibinder.addBinding().toInstance("C");
            }
        });

        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);

        factory.newPage(MyJavaxTest.class);
    }

    /**
     * Test something
     */
    @Test(expected = WicketRuntimeException.class)
    public void testMissingParamsConstructor() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);

        factory.newPage(TestPage99.class);
    }

    /**
     * Test the {@link Multibinder} injection
     */
    @Test
    public void testMultibinderInjectSet() {
        final Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                Multibinder<String> multibinder = Multibinder.newSetBinder(binder(), String.class, Names.named("abc"));
                multibinder.addBinding().toInstance("A");
                multibinder.addBinding().toInstance("B");

                multibinder = Multibinder.newSetBinder(binder(), String.class, Names.named("abc"));
                multibinder.addBinding().toInstance("C");
            }
        });

        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);

        factory.newPage(MyTest.class);
    }

    /**
     * Test something
     */
    @Test(expected = WicketRuntimeException.class)
    public void testNoSuitableConstructor() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);

        factory.newPage(TestPage0.class);
    }

    /**
     * Test something
     */
    @Test(expected = WicketRuntimeException.class)
    public void testNoSuitableConstructorWithParameters() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company", "jolira");

        factory.newPage(TestPage0.class, params);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalBoolean() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPage15.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalByte() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPageOptionalByte.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalChar() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPage17.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalDouble() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPage12.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalEverything() {
        final Injector injector = Guice.createInjector(new Module() {
            @Override
            public void configure(final Binder binder) {
                binder.bind(CONVERTER_TYPE_LITERAL).to(IntegerConverter.class);
            }
        });
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPageOptionalEverything.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalFloat() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPage14.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalInteger() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPageIntOffset.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalLong() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPage16.class);
    }

    /**
     * Test something
     */
    @Test
    public void testOptionalShort() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        factory.newPage(TestPage13.class);
    }

    /**
     *
     */
    @Test
    public void testPageInjectWithParamsOnly() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company", "jolira");

        final IRequestablePage page = factory.newPage(TestPageInjectWithParamsOnly.class, params);

        assertNotNull(page);
    }

    /**
     *
     */
    @Test
    public void testPageNoParameters() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);

        assertNotNull(factory.newPage(TestPage1.class));
        assertNotNull(factory.newPage(TestPage1.class));
    }

    /**
     * Test something
     */
    @Test
    public void testPageParametersOnly() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("company1", "jolira1");
        params.add("company2", "jolira2");

        final IRequestablePage page = factory.newPage(TestPageParamOnly.class, params);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testPageParametersWithNull() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final IRequestablePage page = factory.newPage(TestPageNullParam.class);

        assertNotNull(page);
    }

    /**
     * Test something
     */
    @Test
    public void testUncleansedParams() {
        final Injector injector = Guice.createInjector();
        final GuicierPageFactory factory = injector.getInstance(GuicierPageFactory.class);
        final PageParameters params = new PageParameters();

        params.add("offset", "0");
        params.add("a", "a");
        params.add("b", "b");

        factory.newPage(TestUncleansedParams.class, params);
    }
}
