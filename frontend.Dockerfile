# ---------- build ----------
FROM node:18-alpine AS build
WORKDIR /app

COPY frontend/package*.json ./
RUN npm ci

COPY frontend/ .

# 👇 Fix Node 17+/18 + webpack crypto issue
ENV NODE_OPTIONS=--openssl-legacy-provider

# Build straight into /tmp/www (works for Angular ≤16 and ≥17)
RUN npm run build -- --configuration production --output-path=/tmp/www

# ---------- runtime ----------
FROM nginx:1.27-alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /tmp/www /usr/share/nginx/html
EXPOSE 80
