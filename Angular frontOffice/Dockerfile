# stage 1
FROM node:16-alpine AS build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# stage 2
FROM nginx:alpine
COPY --from=build /app/dist/front-office-pi-dev /usr/share/nginx/html
EXPOSE 80
