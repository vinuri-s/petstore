package org.acme;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeGreetingResourceIT extends PetResourceTest {

    // Execute the same tests but in native mode.
}