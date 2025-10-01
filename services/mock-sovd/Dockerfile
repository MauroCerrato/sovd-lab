# SPDX-License-Identifier: Apache-2.0
FROM python:3.11-slim

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
COPY main.py .

EXPOSE 8080

# Binding uvicorn and main app to 0.0.0.0:8080 (not only localhost) inside the code
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8080"]
