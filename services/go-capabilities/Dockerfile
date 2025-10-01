# Build stage
FROM golang:1.22-alpine AS build
WORKDIR /src
COPY go.mod ./
RUN go mod download
COPY . .
RUN CGO_ENABLED=0 go build -o /out/go-capabilities

# Runtime stage
FROM alpine:3.20
RUN apk add --no-cache curl
WORKDIR /app
COPY --from=build /out/go-capabilities /app/go-capabilities
ENV PORT=8085
EXPOSE 8085
ENTRYPOINT ["/app/go-capabilities"]