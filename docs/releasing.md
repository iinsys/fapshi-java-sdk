# Releasing the Fapshi Java SDK

This guide explains how to publish the SDK for others to use as a dependency, and how to manage versions for development and stable releases.

---

## 1. Versioning: SNAPSHOT vs. Release

- **SNAPSHOT** versions (e.g., `0.1.0-SNAPSHOT`) are for ongoing development and may change at any time.
- **Release** versions (e.g., `1.0.0`) are stable and should not change after publishing.

**Best practice:**
- Use `-SNAPSHOT` for development.
- Use semantic versioning (e.g., `1.0.0`, `1.1.0`, `2.0.0`) for releases.

---

## 2. Publishing Options

### A. Maven Central (Recommended for Open Source)

1. **Register with Sonatype OSSRH:**
   - https://central.sonatype.org/publish/publish-guide/
2. **Configure your `pom.xml`:**
   - Add project metadata (name, description, license, SCM, developers, etc.).
3. **Sign your artifacts:**
   - GPG key required.
4. **Deploy using Maven:**
   ```sh
   mvn clean deploy -P release
   ```
5. **Wait for approval** (first time only), then your artifact will be available for anyone to use.

**Users can then add:**
```xml
<dependency>
    <groupId>com.fapshi</groupId>
    <artifactId>fapshi-java-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

---

### B. JitPack (Quick and Easy for GitHub Projects)

1. **Push your code and tag a release** on GitHub (e.g., `v1.0.0`).
2. **Users add this to their `pom.xml`:**
   ```xml
   <repository>
     <id>jitpack.io</id>
     <url>https://jitpack.io</url>
   </repository>
   <dependency>
     <groupId>com.github.iinsys</groupId>
     <artifactId>fapshi-java-sdk</artifactId>
     <version>v1.0.0</version>
   </dependency>
   ```
3. **JitPack** will build and serve your release automatically.

---

## 3. Tagging a Release

1. **Update your `pom.xml`** to a release version (e.g., `1.0.0`).
2. **Commit and push.**
3. **Tag the release:**
   ```sh
   git tag v1.0.0
   git push origin v1.0.0
   ```
4. (If using JitPack, you’re done! If using Maven Central, follow the deploy steps above.)

---

## 4. Switching Between SNAPSHOT and Release

- For development:  `0.2.0-SNAPSHOT`
- For release:      `1.0.0`

Update the `<version>` in your `pom.xml` accordingly.

---

## 5. Summary Table

| Use Case         | Version Example   | How to Use/Publish                |
|------------------|------------------|-----------------------------------|
| Development      | 0.2.0-SNAPSHOT   | Not for public use                |
| Release/Stable   | 1.0.0            | Tag, publish to Maven/JitPack     |

---

For more details, see the official [Maven Central guide](https://central.sonatype.org/publish/publish-guide/) or [JitPack documentation](https://jitpack.io/docs/). 