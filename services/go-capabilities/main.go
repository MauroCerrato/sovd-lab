package main

import (
    "encoding/json"
    "log"
    "net/http"
    "os"
    "time"

    "github.com/go-chi/chi/v5"
)

type VinPayload struct {
    VIN string `json:"vin"`
}

var currentVIN = "VEHICLEOBDTEST01" // synthetic default aligned with obd2-sovd-sim service

func getVIN(w http.ResponseWriter, r *http.Request) {
    resp := VinPayload{VIN: currentVIN}
    w.Header().Set("Content-Type", "application/json")
    json.NewEncoder(w).Encode(resp)
}

// Simple PUT with a guard to simulate "not ready" error patterns later
func putVIN(w http.ResponseWriter, r *http.Request) {
    var p VinPayload
    if err := json.NewDecoder(r.Body).Decode(&p); err != nil || p.VIN == "" {
        http.Error(w, "invalid payload", http.StatusBadRequest)
        return
    }
    currentVIN = p.VIN
    w.WriteHeader(http.StatusNoContent)
}

func health(w http.ResponseWriter, r *http.Request) {
    w.WriteHeader(http.StatusOK)
    w.Write([]byte("ok"))
}

func main() {
    addr := ":" + getenv("PORT", "8085")
    r := chi.NewRouter()
    r.Get("/healthz", health)
    r.Get("/data/ident/vin", getVIN)
    r.Put("/data/ident/vin", putVIN)

    srv := &http.Server{
        Addr:         addr,
        Handler:      r,
        ReadTimeout:  5 * time.Second,
        WriteTimeout: 5 * time.Second,
        IdleTimeout:  60 * time.Second,
    }

    log.Printf("go-capabilities listening on %s", addr)
    log.Fatal(srv.ListenAndServe())
}

func getenv(k, def string) string {
    if v := os.Getenv(k); v != "" {
        return v
    }
    return def
}
