FROM python:3.10-slim

# Good defaults for containers
ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    PIP_DISABLE_PIP_VERSION_CHECK=1 \
    PIP_NO_CACHE_DIR=1

WORKDIR /app

# Add curl so compose healthchecks work inside the container
RUN apt-get update \
 && apt-get install -y --no-install-recommends curl ca-certificates \
 && rm -rf /var/lib/apt/lists/*

# Layer pip install before copying the rest to maximize cache hits
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Now copy the app code
COPY server.py .

EXPOSE 8080

# Ensure server.py binds to 0.0.0.0:8080 (not only localhost) inside the code
CMD ["python", "server.py"]
