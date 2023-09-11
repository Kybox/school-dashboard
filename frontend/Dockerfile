### Stage: 1 ###
FROM node:18-alpine3.17 as Build
WORKDIR /app
COPY ./package.json /app/
RUN npm install
COPY . /app
RUN npm run build:ssr

### Stage: 2 ###
FROM node:18-alpine3.17
WORKDIR /app
COPY --from=Build /app/package.json /app
COPY --from=Build /app/dist /app/dist

EXPOSE 4000

CMD ["npm", "run", "serve:ssr"]