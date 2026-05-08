FROM maven:3.9-eclipse-temurin-11

WORKDIR /workspace

# Install Chrome
RUN apt-get update && apt-get install -y \
    chromium-browser \
    chromium-chromedriver \
    firefox-esr \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

# Copy project
COPY . .

# Build project
RUN mvn clean install -DskipTests

# Run tests
CMD ["mvn", "clean", "test"]
