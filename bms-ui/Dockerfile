# Step 1: Build Angular App
FROM node:20-alpine AS builder
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build

# Step 2: Serve using Nginx
FROM nginx:alpine
COPY --from=builder /app/dist/bms-ui/browser /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
EXPOSE 80