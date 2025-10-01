# SPDX-License-Identifier: Apache-2.0
FROM python:3.10-slim

# Good defaults for containers
ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    PIP_DISABLE_PIP_VERSION_CHECK=1 \
    PIP_NO_CACHE_DIR=1

WORKDIR /app

# Adding curl so compose healthchecks work inside the container
RUN apt-get update \
 && apt-get install -y --no-install-recommends curl ca-certificates \
 && rm -rf /var/lib/apt/lists/*

# Installing dependencies ...
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copying the app code ...
COPY server.py .

EXPOSE 8080

# Binding server.py to 0.0.0.0:8080 (not only localhost) inside the code
CMD ["python", "server.py"]
